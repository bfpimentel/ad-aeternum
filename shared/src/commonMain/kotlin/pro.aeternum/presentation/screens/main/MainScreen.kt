package pro.aeternum.presentation.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import pro.aeternum.di.component
import pro.aeternum.presentation.navigation.AdAeternumDestination
import pro.aeternum.presentation.screens.liturgy.LiturgyScreen
import pro.aeternum.presentation.screens.main.state.MainActions
import pro.aeternum.presentation.screens.main.state.MainState
import pro.aeternum.presentation.state.Store
import pro.aeternum.presentation.state.transientComposableStore

@Composable
internal fun MainScreen() {
    val coroutineScope = rememberCoroutineScope()
    val store: Store<MainState, MainActions> = transientComposableStore {
        component.presentationModule.provideMainStore(
            coroutineScope = coroutineScope,
            restoredState = null, // todo
        )
    }
    val currentState by store.state.collectAsState()

    MainScreenContent(
        currentState = currentState,
        navigate = { destination -> store.dispatch(MainActions.Navigate(destination = destination)) },
    )
}

@Composable
private fun MainScreenContent(
    currentState: MainState,
    navigate: (AdAeternumDestination) -> Unit,
) {
    if (currentState.destination is AdAeternumDestination.FullScreen) {
        Text("Full Screen TBD.")
        return
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = {
                Text(
                    text = "Ad Aeternum",
                    style = MaterialTheme.typography.displayMedium,
                )
            }
        )

        when (currentState.destination) {
            is AdAeternumDestination.NavBarScreen -> Column(modifier = Modifier.weight(1f)) {
                currentState.destination.Content(navigate = navigate)
            }
            is AdAeternumDestination.Dialog -> Text("Dialog TBD.")
            is AdAeternumDestination.FullScreen -> Unit // shouldn't ever happen
        }

        MainNavBar(
            currentState = currentState,
            navigate = navigate
        )
    }
}

@Composable
private fun MainNavBar(
    currentState: MainState,
    navigate: (AdAeternumDestination) -> Unit,
) {
    val screens: List<AdAeternumDestination.NavBarScreen> = listOf(
        LiturgyScreen,
        PlaceholderScreen,
    )

    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        screens.forEach { screen ->
            NavigationBarItem(
                modifier = Modifier.weight(1f),
                selected = currentState.destination == screen,
                onClick = { navigate(screen) },
                icon = { Text(screen.id) }
            )
        }
    }
}

private data object PlaceholderScreen : AdAeternumDestination.NavBarScreen {

    override val id: String = "placeholder"

    @Composable
    override fun Content(navigate: (AdAeternumDestination) -> Unit) {
        Text("Placeholder")
    }
}
