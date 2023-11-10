package pro.aeternum.presentation.navigation

import androidx.compose.runtime.Composable

internal sealed interface Destination {

    interface NavBarScreen : Destination

    interface FullScreen : Destination

    interface Dialog : Destination

    val title: String

    @Composable
    fun Content()
}
