package pro.aeternum.presentation.screens.rosarylist.state

import pro.aeternum.domain.model.RosariesItem

internal sealed interface RosariesListAction {

    data object Load : RosariesListAction

    data object SetIsLoading : RosariesListAction

    data class SetRosaries(val rosaries: List<RosariesItem>) : RosariesListAction

    data class SelectRosary(val id: String) : RosariesListAction

    data object SetHasError : RosariesListAction
}
