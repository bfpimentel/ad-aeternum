package pro.aeternum.presentation.screens.third.state

import pro.aeternum.domain.usecase.GetThirdUseCase
import pro.aeternum.presentation.navigation.Navigator
import pro.aeternum.presentation.state.SideEffect

internal class ThirdSideEffects(
    private val thirdId: String,
    private val getThird: GetThirdUseCase,
    private val navigator: Navigator,
) {

    fun get(): SideEffect<ThirdState, ThirdActions> = { _, action ->
        when (action) {
            is ThirdActions.Load -> try {
                this(ThirdActions.SetIsLoading)
                val third = getThird(id = thirdId)
                this(ThirdActions.SetThird(third = third))
            } catch (exception: Exception) {
                this(ThirdActions.SetHasError)
            }
            is ThirdActions.NavigateBack -> navigator.navigateBack()
            else -> Unit
        }
    }
}
