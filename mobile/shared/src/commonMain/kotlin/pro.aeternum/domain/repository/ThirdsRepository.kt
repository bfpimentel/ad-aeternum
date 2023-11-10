package pro.aeternum.domain.repository

import pro.aeternum.data.model.ThirdItemResponse
import pro.aeternum.data.model.ThirdResponse

internal interface ThirdsRepository {

    suspend fun getThirdsList(): List<ThirdItemResponse>

    suspend fun getSingleThird(id: String): ThirdResponse
}
