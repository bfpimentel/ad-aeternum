package pro.aeternum.data.source.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import pro.aeternum.data.model.LiturgyResponse

internal class LiturgyRemoteSource(private val client: HttpClient) {

    suspend fun getLiturgy(): LiturgyResponse = client.get("/").body()
}
