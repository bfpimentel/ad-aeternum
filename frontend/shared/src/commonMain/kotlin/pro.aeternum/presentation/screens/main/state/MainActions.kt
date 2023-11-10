package pro.aeternum.presentation.screens.main.state

import pro.aeternum.presentation.navigation.Destination

internal sealed interface MainActions {

    data class Navigate(val destination: Destination) : MainActions
}
