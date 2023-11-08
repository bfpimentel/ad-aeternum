package pro.aeternum.domain.repository

import pro.aeternum.data.model.ThirdResponse

internal interface ThirdsRepository {

    suspend fun getThirds(): ThirdResponse
}
