package pro.aeternum.domain.usecase

import pro.aeternum.data.model.RosariesItemResponse
import pro.aeternum.domain.model.RosariesItem
import pro.aeternum.domain.model.toDomainModel
import pro.aeternum.domain.repository.RosariesRepository

internal class GetRosariesUseCase(
    private val rosariesRepository: RosariesRepository,
) {

    suspend operator fun invoke(): List<RosariesItem> = rosariesRepository.getRosaries()
        .map(RosariesItemResponse::toDomainModel)
}
