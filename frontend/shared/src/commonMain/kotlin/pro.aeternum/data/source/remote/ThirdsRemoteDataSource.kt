package pro.aeternum.data.source.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import pro.aeternum.data.model.ThirdResponse

internal class ThirdsRemoteDataSource(private val client: HttpClient) {

    suspend fun getThirds(): ThirdResponse = client.get("/api/thirds").body<List<ThirdResponse>>().first()
}
