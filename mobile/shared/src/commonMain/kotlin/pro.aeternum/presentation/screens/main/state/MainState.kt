package pro.aeternum.presentation.screens.main.state

import pro.aeternum.presentation.navigation.Destination
import pro.aeternum.presentation.screens.thirdlist.ThirdListScreen

internal data class MainState(
    val destination: Destination,
    val destinationStack: List<Destination>,
) {

    companion object {
        val INITIAL = MainState(
            destination = ThirdListScreen,
            destinationStack = listOf(ThirdListScreen),
        )
    }
}
