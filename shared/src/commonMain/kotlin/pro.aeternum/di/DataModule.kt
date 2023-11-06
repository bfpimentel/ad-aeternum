package pro.aeternum.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import pro.aeternum.data.api.LiturgyAPI

internal interface DataModule {
    val liturgyAPI: LiturgyAPI
}

internal class DefaultDataModule : DataModule {

    override val liturgyAPI: LiturgyAPI
        get() = LiturgyAPI(client = provideHttpClient(baseURL = "https://liturgia.up.railway.app"))

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
