package pro.aeternum.presentation.screens.liturgy.state

import pro.aeternum.presentation.state.Reducer

internal object LiturgyReducer {

    operator fun invoke(): Reducer<LiturgyState, LiturgyActions> = { state, action ->
        when (action) {
            is LiturgyActions.SetLiturgy -> state.copy(
                isLoading = false,
                text = action.text,
            )
            else -> state
        }
    }
}
