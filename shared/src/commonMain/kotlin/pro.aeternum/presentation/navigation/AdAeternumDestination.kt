package pro.aeternum.presentation.navigation

import androidx.compose.runtime.Composable

internal sealed interface AdAeternumDestination {

    interface NavBarScreen : AdAeternumDestination

    interface FullScreen : AdAeternumDestination

    interface Dialog : AdAeternumDestination

    val id: String

    @Composable
    fun Content(navigate: (AdAeternumDestination) -> Unit)
}
