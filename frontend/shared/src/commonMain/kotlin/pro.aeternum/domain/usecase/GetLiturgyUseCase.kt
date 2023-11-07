package pro.aeternum.domain.usecase

import pro.aeternum.domain.model.Liturgy
import pro.aeternum.domain.repository.LiturgyRepository

internal class GetLiturgyUseCase(
    private val liturgyRepository: LiturgyRepository,
) {

    suspend operator fun invoke(): Liturgy = liturgyRepository.getLiturgy()
}
