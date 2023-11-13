package pro.aeternum.presentation.screens.main.state

import pro.aeternum.presentation.navigation.Destination
import pro.aeternum.presentation.state.Reducer

internal object MainReducer {

    operator fun invoke(): Reducer<MainState, MainActions> = { state, action ->
        when (action) {
            is MainActions.Navigate -> {
                when (val destination = action.destination) {
                    is Destination.Dialog -> state.copy(
                        destination = destination,
                        destinationStack = state.destinationStack + destination
                    )
                    is Destination.FullScreen -> state.copy(
                        destination = destination,
                        destinationStack = state.destinationStack + destination
                    )
                    is Destination.NavBarScreen -> state.copy(
                        destination = destination,
                        destinationStack = listOf(destination)
                    )
                }
            }
            is MainActions.NavigateBack -> {
                val destinationStack = state.destinationStack.dropLast(1)

                if (destinationStack.isNotEmpty()) {
                    state.copy(
                        destination = destinationStack.last(),
                        destinationStack = destinationStack,
                    )
                } else {
                    state
                }
            }
        }
    }
}
