package pro.aeternum.presentation.screens.third.state

internal sealed interface ThirdActions {

    data object Load : ThirdActions

    data class SetThird(val text: String) : ThirdActions
}
