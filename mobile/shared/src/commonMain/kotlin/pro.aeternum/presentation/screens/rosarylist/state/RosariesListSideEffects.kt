package pro.aeternum.presentation.screens.rosarylist.state

import pro.aeternum.domain.usecase.GetRosariesUseCase
import pro.aeternum.presentation.navigation.Navigator
import pro.aeternum.presentation.screens.rosary.RosaryScreen
import pro.aeternum.presentation.state.SideEffect

internal class RosariesListSideEffects(
    private val getRosaries: GetRosariesUseCase,
    private val navigator: Navigator,
) {

    fun get(): SideEffect<RosariesListState, RosariesListAction> = { _, action ->
        when (action) {
            is RosariesListAction.Load -> {
                try {
                    this(RosariesListAction.SetIsLoading)
                    val rosaries = getRosaries()
                    this(RosariesListAction.SetRosaries(rosaries = rosaries))
                } catch (exception: Exception) {
                    this(RosariesListAction.SetHasError)
                }
            }
            is RosariesListAction.SelectRosary -> navigator.navigate(
                destination = RosaryScreen.create(rosaryId = action.id)
            )
            else -> Unit
        }
    }
}
