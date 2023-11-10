package pro.aeternum.data.source.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import pro.aeternum.data.model.ThirdItemResponse
import pro.aeternum.data.model.ThirdResponse
import pro.aeternum.environment.Environment

internal class ThirdsRemoteDataSource(private val client: HttpClient) {

    suspend fun getThirdsList(): List<ThirdItemResponse> =
        client.get(
            urlString = "${Environment.AD_AETERNUM_API_PATH}/third_list"
        ).body()

    suspend fun getSingleThird(id: String): ThirdResponse = client.get(
        urlString = "${Environment.AD_AETERNUM_API_PATH}/third?id=${id}"
    ).body()
}
