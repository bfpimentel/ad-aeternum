package pro.aeternum.presentation.screens.thirdslist.state

import pro.aeternum.presentation.state.Reducer

internal object ThirdsListReducer {

    operator fun invoke(): Reducer<ThirdsListState, ThirdsListActions> = { state, action ->
        when (action) {
            is ThirdsListActions.SetThirdsList -> {
                val thirds = action.thirds.map { third ->
                    ThirdsListState.Third(
                        id = third.id,
                        title = third.title,
                        subtitle = third.subtitle,
                    )
                }

                state.copy(thirds = thirds, isLoading = false)
            }
            else -> state
        }
    }
}
