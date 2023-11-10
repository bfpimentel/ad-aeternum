package pro.aeternum.domain.usecase

import pro.aeternum.domain.model.Third
import pro.aeternum.domain.model.toDomainModel
import pro.aeternum.domain.repository.ThirdsRepository

internal class GetThirdUseCase(
    private val thirdsRepository: ThirdsRepository,
) {

    suspend operator fun invoke(id: String): Third = thirdsRepository.getSingleThird(id = id).toDomainModel()
}
