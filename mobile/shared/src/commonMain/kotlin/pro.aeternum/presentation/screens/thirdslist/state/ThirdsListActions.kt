package pro.aeternum.presentation.screens.thirdslist.state

import pro.aeternum.domain.model.ThirdsListItem

internal sealed interface ThirdsListActions {

    data object Load : ThirdsListActions

    data class SetThirdsList(val thirds: List<ThirdsListItem>) : ThirdsListActions

    data class SelectThird(val id: String) : ThirdsListActions
}
