package pro.aeternum.presentation.screens.main.state

import pro.aeternum.presentation.navigation.AdAeternumDestinations

internal sealed interface MainActions {

    data class Navigate(val destination: AdAeternumDestinations) : MainActions
}
