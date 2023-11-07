package pro.aeternum.presentation.screens.main.state

import pro.aeternum.presentation.navigation.AdAeternumDestination
import pro.aeternum.presentation.screens.liturgy.LiturgyScreen

internal data class MainState(
    val destination: AdAeternumDestination,
) {

    companion object {
        val INITIAL = MainState(
            destination = LiturgyScreen,
        )
    }
}
