package pro.aeternum.presentation.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
import pro.aeternum.presentation.state.Dispatch
import pro.aeternum.presentation.state.Store
import pro.aeternum.presentation.state.composableStore

@Composable
internal fun MainScreen() {
    val coroutineScope = rememberCoroutineScope()
    val store: Store<MainState, MainActions> = composableStore { restoredState ->
        component.presentationModule.provideMainStore(
            coroutineScope = coroutineScope,
            restoredState = restoredState,
        )
    }
    val currentState by store.state.collectAsState()

    MainScreenContent(
        currentState = currentState,
        dispatch = store.dispatch,
    )
}

@Composable
private fun MainScreenContent(
    currentState: MainState,
    dispatch: Dispatch<MainActions>,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = {
                if (currentState.destination is LiturgyScreen) {
                    dispatch(MainActions.Navigate(PlaceholderScreen))
                } else {
                    dispatch(MainActions.Navigate(LiturgyScreen))
                }
            }
        ) { Text("Switch Destination") }

        when (currentState.destination) {
            is AdAeternumDestination.Screen -> Column(modifier = Modifier.weight(1f)) {
                currentState.destination.Content { destination -> dispatch(MainActions.Navigate(destination)) }
            }
            is AdAeternumDestination.Dialog -> Text("TBD.")
        }
    }
}

private data object PlaceholderScreen : AdAeternumDestination.Screen {

    override val id: String = "placeholder"

    @Composable
    override fun Content(navigate: (AdAeternumDestination) -> Unit) {
        Text("Placeholder")
    }
}
