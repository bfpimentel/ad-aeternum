package pro.aeternum.presentation.screens.main.state

import pro.aeternum.presentation.navigation.Destination
import pro.aeternum.presentation.screens.third.ThirdScreen

internal data class MainState(
    val destination: Destination,
) {

    companion object {
        val INITIAL = MainState(
            destination = ThirdScreen,
        )
    }
}
