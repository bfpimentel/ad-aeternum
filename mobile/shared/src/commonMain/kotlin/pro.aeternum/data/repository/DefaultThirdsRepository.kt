package pro.aeternum.data.repository

import pro.aeternum.data.model.ThirdsListItemResponse
import pro.aeternum.data.model.ThirdResponse
import pro.aeternum.data.source.remote.ThirdsRemoteDataSource
import pro.aeternum.domain.repository.ThirdsRepository

internal class DefaultThirdsRepository(
    private val thirdsRemoteDataSource: ThirdsRemoteDataSource,
) : ThirdsRepository {

    override suspend fun getThirdsList(): List<ThirdsListItemResponse> =
        thirdsRemoteDataSource.getThirdsList()

    override suspend fun getSingleThird(id: String): ThirdResponse =
        thirdsRemoteDataSource.getSingleThird(id = id)
}
