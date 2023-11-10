package pro.aeternum.presentation.screens.liturgy.state

import pro.aeternum.domain.usecase.GetLiturgyUseCase
import pro.aeternum.presentation.screens.third.state.ThirdActions
import pro.aeternum.presentation.state.Dispatch
import pro.aeternum.presentation.state.SideEffect

internal class LiturgySideEffects(
    private val getLiturgy: GetLiturgyUseCase,
) : SideEffect<LiturgyState, LiturgyActions> {

    override suspend fun invoke(
        dispatch: Dispatch<LiturgyActions>,
        state: LiturgyState,
        action: LiturgyActions,
    ) {
        when (action) {
            is LiturgyActions.Load -> try {
                dispatch(LiturgyActions.SetLiturgy(text = getLiturgy().text))
            } catch (exception: Exception) {
                print(exception)
            }

            else -> Unit
        }
    }
}
