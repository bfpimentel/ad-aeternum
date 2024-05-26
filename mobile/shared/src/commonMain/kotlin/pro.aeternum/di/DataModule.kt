package pro.aeternum.di

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import pro.aeternum.data.repository.DefaultLiturgyRepository
import pro.aeternum.data.repository.DefaultRosariesRepository
import pro.aeternum.data.source.remote.LiturgyRemoteSource
import pro.aeternum.data.source.remote.RosariesRemoteDataSource
import pro.aeternum.domain.repository.LiturgyRepository
import pro.aeternum.domain.repository.RosariesRepository
import pro.aeternum.environment.Environment

internal interface DataModule {
    fun provideLiturgyRepository(): LiturgyRepository
    fun provideRosariesRepository(): RosariesRepository
}

internal class DefaultDataModule(
    private val platformModule: PlatformModule,
) : DataModule {

    override fun provideLiturgyRepository(): LiturgyRepository = DefaultLiturgyRepository(
        liturgyRemoteSource = provideLiturgyRemoteSource(),
    )

    override fun provideRosariesRepository(): RosariesRepository = DefaultRosariesRepository(
        rosariesRemoteDataSource = provideRosariesRemoteDataSource(),
    )

    private fun provideRosariesRemoteDataSource(): RosariesRemoteDataSource =
        RosariesRemoteDataSource(
            client = provideHttpClient {
                defaultRequest {
                    url(urlString = Environment.adAeternumApiURL)
                }
            }
        )

    private fun provideLiturgyRemoteSource(): LiturgyRemoteSource = LiturgyRemoteSource(
        client = provideHttpClient {
            install(DefaultRequest) {
                url(urlString = "https://liturgia.up.railway.app")
            }
        }
    )

    private fun provideHttpClient(block: HttpClientConfig<*>.() -> Unit): HttpClient = HttpClient {
        block()
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    platformModule.logger.log(message)
                    println(message)
                }
            }
            level = LogLevel.ALL

        }
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                    useAlternativeNames = true
                }
            )
        }
    }
}
