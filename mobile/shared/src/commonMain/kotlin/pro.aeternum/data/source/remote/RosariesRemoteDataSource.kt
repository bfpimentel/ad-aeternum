package pro.aeternum.data.source.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import pro.aeternum.data.model.RosariesItemResponse
import pro.aeternum.data.model.RosaryResponse
import pro.aeternum.environment.Environment

internal class RosariesRemoteDataSource(private val client: HttpClient) {

    suspend fun getRosaries(): List<RosariesItemResponse> =
        client.get(
            urlString = "${Environment.adAeternumApiPath}/rosaries"
        ).body()

    suspend fun getSingleRosary(id: String): RosaryResponse = client.get(
        urlString = "${Environment.adAeternumApiPath}/rosary?id=${id}"
    ).body()
}
