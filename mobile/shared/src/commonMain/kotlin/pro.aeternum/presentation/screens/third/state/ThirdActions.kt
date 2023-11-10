package pro.aeternum.presentation.screens.third.state

import pro.aeternum.domain.model.Third

internal sealed interface ThirdActions {

    data object Load : ThirdActions

    data class SetThird(val third: Third) : ThirdActions

    data object Next : ThirdActions

    data object Previous : ThirdActions
}
