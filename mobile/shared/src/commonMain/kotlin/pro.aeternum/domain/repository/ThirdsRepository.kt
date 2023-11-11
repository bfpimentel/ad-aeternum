package pro.aeternum.domain.repository

import pro.aeternum.data.model.ThirdsListItemResponse
import pro.aeternum.data.model.ThirdResponse

internal interface ThirdsRepository {

    suspend fun getThirdsList(): List<ThirdsListItemResponse>

    suspend fun getSingleThird(id: String): ThirdResponse
}
