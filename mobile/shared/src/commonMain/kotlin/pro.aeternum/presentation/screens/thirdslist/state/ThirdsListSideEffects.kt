package pro.aeternum.presentation.screens.thirdslist.state

import pro.aeternum.domain.usecase.GetThirdsListUseCase
import pro.aeternum.presentation.navigation.Navigator
import pro.aeternum.presentation.screens.third.ThirdScreen
import pro.aeternum.presentation.state.SideEffect

internal class ThirdsListSideEffects(
    private val getThirdsList: GetThirdsListUseCase,
    private val navigator: Navigator,
) {

    fun get(): SideEffect<ThirdsListState, ThirdsListActions> = { _, action ->
        when (action) {
            is ThirdsListActions.Load -> {
                val thirds = getThirdsList()
                this(ThirdsListActions.SetThirdsList(thirds = thirds))
            }
            is ThirdsListActions.SelectThird -> navigator.navigate(
                destination = ThirdScreen(thirdId = action.id)
            )
            else -> Unit
        }
    }
}
