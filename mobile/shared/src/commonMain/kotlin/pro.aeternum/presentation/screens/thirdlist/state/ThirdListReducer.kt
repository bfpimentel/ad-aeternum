package pro.aeternum.presentation.screens.thirdlist.state

import pro.aeternum.presentation.state.Reducer

internal object ThirdListReducer {

    operator fun invoke(): Reducer<ThirdListState, ThirdListActions> = { state, action ->
        when (action) {
            is ThirdListActions.SetIsLoading -> state.copy(isLoading = true, hasError = false)
            is ThirdListActions.SetThirdList -> {
                val thirds = action.thirds.map { third ->
                    ThirdListState.Third(
                        id = third.id,
                        title = third.title,
                        subtitle = third.subtitle,
                    )
                }

                state.copy(thirds = thirds, isLoading = false)
            }
            is ThirdListActions.SetHasError -> state.copy(hasError = true)
            else -> state
        }
    }
}
