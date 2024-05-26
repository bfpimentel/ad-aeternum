package pro.aeternum.presentation.screens.main.state

import pro.aeternum.presentation.navigation.Destination
import pro.aeternum.presentation.screens.rosarylist.RosariesListScreen

internal data class MainState(
    val destination: Destination,
    val destinationStack: List<Destination>,
) {

    companion object {
        val INITIAL = MainState(
            destination = RosariesListScreen,
            destinationStack = listOf(RosariesListScreen),
        )
    }
}
