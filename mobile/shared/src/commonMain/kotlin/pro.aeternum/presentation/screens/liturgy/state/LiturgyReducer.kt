package pro.aeternum.presentation.screens.liturgy.state

import pro.aeternum.presentation.state.Reducer

internal object LiturgyReducer {

    operator fun invoke(): Reducer<LiturgyState, LiturgyActions> = { state, action ->
        when (action) {
            is LiturgyActions.SetIsLoading -> state.copy(isLoading = true, hasError = false)
            is LiturgyActions.SetLiturgy -> state.copy(isLoading = false, text = action.text)
            is LiturgyActions.SetHasError -> state.copy(hasError = true)
            else -> state
        }
    }
}
