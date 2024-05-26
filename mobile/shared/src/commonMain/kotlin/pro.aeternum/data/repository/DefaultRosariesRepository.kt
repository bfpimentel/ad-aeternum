package pro.aeternum.data.repository

import pro.aeternum.data.model.RosariesItemResponse
import pro.aeternum.data.model.RosaryResponse
import pro.aeternum.data.source.remote.RosariesRemoteDataSource
import pro.aeternum.domain.repository.RosariesRepository

internal class DefaultRosariesRepository(
    private val rosariesRemoteDataSource: RosariesRemoteDataSource,
) : RosariesRepository {

    override suspend fun getRosaries(): List<RosariesItemResponse> =
        rosariesRemoteDataSource.getRosaries()

    override suspend fun getSingleRosary(id: String): RosaryResponse =
        rosariesRemoteDataSource.getSingleRosary(id = id)
}
