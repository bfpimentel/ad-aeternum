package pro.aeternum.presentation.screens.main.state

import pro.aeternum.presentation.navigation.AdAeternumDestinations

internal data class MainState(
    val destination: AdAeternumDestinations,
) {

    companion object {
        val INITIAL = MainState(
            destination = AdAeternumDestinations.Liturgy,
        )
    }
}
