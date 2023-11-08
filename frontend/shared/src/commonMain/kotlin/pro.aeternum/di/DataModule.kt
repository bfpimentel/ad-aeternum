package pro.aeternum.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import pro.aeternum.data.repository.DefaultLiturgyRepository
import pro.aeternum.data.repository.DefaultThirdsRepository
import pro.aeternum.data.source.remote.LiturgyRemoteSource
import pro.aeternum.data.source.remote.ThirdsRemoteDataSource
import pro.aeternum.domain.repository.LiturgyRepository
import pro.aeternum.domain.repository.ThirdsRepository
import pro.aeternum.environment.Environment

internal interface DataModule {
    fun provideLiturgyRepository(): LiturgyRepository
    fun provideThirdsRepository(): ThirdsRepository
}

internal class DefaultDataModule(
    private val platformModule: PlatformModule,
) : DataModule {

    override fun provideLiturgyRepository(): LiturgyRepository = DefaultLiturgyRepository(
        liturgyRemoteSource = provideLiturgyRemoteSource(),
    )

    override fun provideThirdsRepository(): ThirdsRepository = DefaultThirdsRepository(
        thirdsRemoteDataSource = provideThirdsRemoteDataSource(),
    )

    private fun provideThirdsRemoteDataSource(): ThirdsRemoteDataSource = ThirdsRemoteDataSource(
        client = provideHttpClient(baseURL = Environment.AD_AETERNUM_API_URL)
    )

    private fun provideLiturgyRemoteSource(): LiturgyRemoteSource = LiturgyRemoteSource(
        client = provideHttpClient(baseURL = "https://liturgia.up.railway.app")
    )

    private fun provideHttpClient(baseURL: String): HttpClient = HttpClient {
        install(DefaultRequest) { url(baseURL) }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    platformModule.logger.log(message)
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
