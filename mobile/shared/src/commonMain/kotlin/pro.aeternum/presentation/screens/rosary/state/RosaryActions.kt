package pro.aeternum.presentation.screens.rosary.state

import pro.aeternum.domain.model.Rosary

internal sealed interface RosaryActions {

    data object Load : RosaryActions

    data object SetIsLoading : RosaryActions

    data class SetRosary(val rosary: Rosary) : RosaryActions

    data class Swipe(val index: Int) : RosaryActions

    data object NavigateBack : RosaryActions

    data object SetHasError : RosaryActions
}
