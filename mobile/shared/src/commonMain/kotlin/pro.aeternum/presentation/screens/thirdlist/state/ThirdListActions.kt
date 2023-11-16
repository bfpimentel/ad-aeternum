package pro.aeternum.presentation.screens.thirdlist.state

import pro.aeternum.domain.model.ThirdsListItem

internal sealed interface ThirdListActions {

    data object Load : ThirdListActions

    data object SetIsLoading : ThirdListActions

    data class SetThirdList(val thirds: List<ThirdsListItem>) : ThirdListActions

    data class SelectThird(val id: String) : ThirdListActions

    data object SetHasError : ThirdListActions
}
