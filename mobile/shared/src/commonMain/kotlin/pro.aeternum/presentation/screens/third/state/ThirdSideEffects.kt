package pro.aeternum.presentation.screens.third.state

import pro.aeternum.domain.usecase.GetThirdUseCase
import pro.aeternum.presentation.state.SideEffect

internal class ThirdSideEffects(
    private val getThird: GetThirdUseCase,
) {

    fun get(): SideEffect<ThirdState, ThirdActions> = { state, action ->
        when (action) {
            is ThirdActions.Load -> try {
                val third = getThird(id = "joyful_mysteries")
                this(ThirdActions.SetThird(third = third))
            } catch (exception: Exception) {
                print(exception)
            }
            else -> Unit
        }
    }
}
