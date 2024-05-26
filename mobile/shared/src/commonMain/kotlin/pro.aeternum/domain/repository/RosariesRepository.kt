package pro.aeternum.domain.repository

import pro.aeternum.data.model.RosariesItemResponse
import pro.aeternum.data.model.RosaryResponse

internal interface RosariesRepository {

    suspend fun getRosaries(): List<RosariesItemResponse>

    suspend fun getSingleRosary(id: String): RosaryResponse
}
