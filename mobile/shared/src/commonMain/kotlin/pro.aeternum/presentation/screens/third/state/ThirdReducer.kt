package pro.aeternum.presentation.screens.third.state

import pro.aeternum.presentation.state.Reducer

internal object ThirdReducer {

    operator fun invoke(): Reducer<ThirdState, ThirdActions> = { state, action ->
        when (action) {
            is ThirdActions.SetThird -> state.copy(
                isLoading = false,
                text = action.text,
            )
            else -> state
        }
    }
}
