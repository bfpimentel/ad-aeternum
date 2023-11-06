package pro.aeternum.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import pro.aeternum.data.repository.DefaultLiturgyRepository
import pro.aeternum.data.repository.DefaultThirdRepository
import pro.aeternum.data.source.local.ThirdLocalSource
import pro.aeternum.data.source.remote.LiturgyRemoteSource
import pro.aeternum.domain.repository.LiturgyRepository
import pro.aeternum.domain.repository.ThirdRepository

internal interface DataModule {
    fun provideLiturgyRepository(): LiturgyRepository
    fun provideThirdRepository(): ThirdRepository
}

internal class DefaultDataModule(
    private val platformModule: PlatformModule,
) : DataModule {

    override fun provideLiturgyRepository(): LiturgyRepository = DefaultLiturgyRepository(
        liturgyRemoteSource = provideLiturgyRemoteSource(),
    )

    override fun provideThirdRepository(): ThirdRepository = DefaultThirdRepository(
        thirdLocalSource = provideThirdLocalSource()
    )

    private fun provideLiturgyRemoteSource(): LiturgyRemoteSource = LiturgyRemoteSource(
        client = provideHttpClient(baseURL = "https://liturgia.up.railway.app")
    )

    private fun provideThirdLocalSource(): ThirdLocalSource = ThirdLocalSource(
        localeGetter = platformModule.localeGetter,
        fileReader = platformModule.fileReader,
        ioDispatcher = platformModule.threads.io,
    )

    private fun provideHttpClient(baseURL: String): HttpClient = HttpClient {
        install(DefaultRequest) { url(baseURL) }
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
