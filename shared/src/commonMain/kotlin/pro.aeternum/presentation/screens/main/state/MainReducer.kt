package pro.aeternum.presentation.screens.main.state

import pro.aeternum.presentation.state.Reducer

internal object MainReducer {

    operator fun invoke(): Reducer<MainState, MainActions> = { state, action ->
        when (action) {
            is MainActions.Navigate -> state.copy(destination = action.destination)
            else -> state
        }
    }
}
