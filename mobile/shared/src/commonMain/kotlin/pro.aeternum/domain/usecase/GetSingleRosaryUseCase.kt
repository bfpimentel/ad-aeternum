package pro.aeternum.domain.usecase

import pro.aeternum.domain.model.Rosary
import pro.aeternum.domain.model.toDomainModel
import pro.aeternum.domain.repository.RosariesRepository

internal class GetSingleRosaryUseCase(
    private val rosariesRepository: RosariesRepository,
) {

    suspend operator fun invoke(id: String): Rosary =
        rosariesRepository.getSingleRosary(id = id).toDomainModel()
}
