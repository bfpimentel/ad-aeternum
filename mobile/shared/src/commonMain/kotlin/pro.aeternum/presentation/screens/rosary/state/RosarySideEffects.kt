package pro.aeternum.presentation.screens.rosary.state

import pro.aeternum.domain.usecase.GetSingleRosaryUseCase
import pro.aeternum.presentation.navigation.Navigator
import pro.aeternum.presentation.state.SideEffect

internal class RosarySideEffects(
    private val rosaryId: String,
    private val getSingleRosary: GetSingleRosaryUseCase,
    private val navigator: Navigator,
) {

    fun get(): SideEffect<RosaryState, RosaryActions> = { _, action ->
        when (action) {
            is RosaryActions.Load -> try {
                this(RosaryActions.SetIsLoading)
                val rosary = getSingleRosary(id = rosaryId)
                this(RosaryActions.SetRosary(rosary = rosary))
            } catch (exception: Exception) {
                this(RosaryActions.SetHasError)
            }
            is RosaryActions.NavigateBack -> navigator.navigateBack()
            else -> Unit
        }
    }
}
