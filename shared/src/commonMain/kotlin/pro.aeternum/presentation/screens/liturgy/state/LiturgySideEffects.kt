package pro.aeternum.presentation.screens.liturgy.state

import pro.aeternum.di.component
import pro.aeternum.domain.repository.ThirdRepository
import pro.aeternum.domain.usecase.GetLiturgyUseCase
import pro.aeternum.presentation.state.Dispatch
import pro.aeternum.presentation.state.SideEffect

internal class LiturgySideEffects(
    private val getLiturgy: GetLiturgyUseCase,
    private val thirdRepository: ThirdRepository = component.dataModule.provideThirdRepository(),
) : SideEffect<LiturgyState, LiturgyActions> {

    override suspend fun invoke(
        dispatch: Dispatch<LiturgyActions>,
        state: LiturgyState,
        action: LiturgyActions,
    ) {
        when (action) {
            is LiturgyActions.Load -> try {
//                val liturgy = getLiturgy()
                val text = thirdRepository.getHailMary()
                dispatch(LiturgyActions.SetLiturgy(text = text))
            } catch (exception: Exception) {
                print(exception)
            }
            else -> Unit
        }
    }
}
