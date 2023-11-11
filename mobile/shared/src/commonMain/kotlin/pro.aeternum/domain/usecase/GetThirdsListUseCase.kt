package pro.aeternum.domain.usecase

import pro.aeternum.data.model.ThirdsListItemResponse
import pro.aeternum.domain.model.ThirdsListItem
import pro.aeternum.domain.model.toDomainModel
import pro.aeternum.domain.repository.ThirdsRepository

internal class GetThirdsListUseCase(
    private val thirdsRepository: ThirdsRepository,
) {

    suspend operator fun invoke(): List<ThirdsListItem> = thirdsRepository.getThirdsList()
        .map(ThirdsListItemResponse::toDomainModel)
}
