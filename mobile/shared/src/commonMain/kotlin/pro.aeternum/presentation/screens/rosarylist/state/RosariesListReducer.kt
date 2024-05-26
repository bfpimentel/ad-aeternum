package pro.aeternum.presentation.screens.rosarylist.state

import pro.aeternum.presentation.state.Reducer

internal object RosariesListReducer {

    operator fun invoke(): Reducer<RosariesListState, RosariesListAction> = { state, action ->
        when (action) {
            is RosariesListAction.SetIsLoading -> state.copy(isLoading = true, hasError = false)
            is RosariesListAction.SetRosaries -> {
                val rosaries = action.rosaries.map { rosary ->
                    RosariesListState.Rosary(
                        id = rosary.id,
                        title = rosary.title,
                        subtitle = rosary.subtitle,
                    )
                }

                state.copy(rosaries = rosaries, isLoading = false)
            }
            is RosariesListAction.SetHasError -> state.copy(hasError = true)
            else -> state
        }
    }
}
