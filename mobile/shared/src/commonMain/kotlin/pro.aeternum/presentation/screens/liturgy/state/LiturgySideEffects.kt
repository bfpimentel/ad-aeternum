package pro.aeternum.presentation.screens.liturgy.state

import pro.aeternum.domain.usecase.GetLiturgyUseCase
import pro.aeternum.presentation.state.SideEffect

internal class LiturgySideEffects(
    private val getLiturgy: GetLiturgyUseCase,
) {

    fun get(): SideEffect<LiturgyState, LiturgyActions> = { state, action ->
        when (action) {
            is LiturgyActions.Load -> try {
                this(LiturgyActions.SetLiturgy(text = getLiturgy().text))
            } catch (exception: Exception) {
                print(exception)
            }

            else -> Unit
        }
    }
}
