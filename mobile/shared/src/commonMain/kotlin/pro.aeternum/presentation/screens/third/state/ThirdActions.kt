package pro.aeternum.presentation.screens.third.state

import pro.aeternum.domain.model.Third

internal sealed interface ThirdActions {

    data object Load : ThirdActions

    data object SetIsLoading : ThirdActions

    data class SetThird(val third: Third) : ThirdActions

    data class Swipe(val index: Int) : ThirdActions

    data object NavigateBack : ThirdActions

    data object SetHasError : ThirdActions
}
