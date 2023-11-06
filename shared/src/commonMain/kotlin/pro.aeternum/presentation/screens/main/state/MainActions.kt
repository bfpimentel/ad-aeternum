package pro.aeternum.presentation.screens.main.state

import pro.aeternum.presentation.navigation.AdAeternumDestination

internal sealed interface MainActions {

    data class Navigate(val destination: AdAeternumDestination) : MainActions
}
