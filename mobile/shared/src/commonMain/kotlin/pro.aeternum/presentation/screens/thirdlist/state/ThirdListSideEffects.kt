package pro.aeternum.presentation.screens.thirdlist.state

import pro.aeternum.domain.usecase.GetThirdsListUseCase
import pro.aeternum.presentation.navigation.Navigator
import pro.aeternum.presentation.screens.third.ThirdScreen
import pro.aeternum.presentation.state.SideEffect

internal class ThirdListSideEffects(
    private val getThirdsList: GetThirdsListUseCase,
    private val navigator: Navigator,
) {

    fun get(): SideEffect<ThirdListState, ThirdListActions> = { _, action ->
        when (action) {
            is ThirdListActions.Load -> {
                val thirds = getThirdsList()
                this(ThirdListActions.SetThirdList(thirds = thirds))
            }
            is ThirdListActions.SelectThird -> navigator.navigate(
                destination = ThirdScreen.create(thirdId = action.id)
            )
            else -> Unit
        }
    }
}
