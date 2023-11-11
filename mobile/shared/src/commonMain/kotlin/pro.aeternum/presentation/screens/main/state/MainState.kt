package pro.aeternum.presentation.screens.main.state

import pro.aeternum.presentation.navigation.Destination
import pro.aeternum.presentation.screens.thirdslist.ThirdsListScreen

internal data class MainState(
    val destination: Destination,
) {

    companion object {
        val INITIAL = MainState(
            destination = ThirdsListScreen(),
        )
    }
}
