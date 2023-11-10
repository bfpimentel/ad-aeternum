package pro.aeternum.presentation.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import pro.aeternum.di.component
import pro.aeternum.presentation.navigation.Destination
import pro.aeternum.presentation.screens.liturgy.LiturgyScreen
import pro.aeternum.presentation.screens.main.state.MainActions
import pro.aeternum.presentation.screens.main.state.MainState
import pro.aeternum.presentation.screens.third.ThirdScreen
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

    component.presentationModule.registerNavigator { destination ->
        store.dispatch(MainActions.Navigate(destination = destination))
    }

    MainScreenContent(
        currentState = currentState,
        navigate = { destination -> store.dispatch(MainActions.Navigate(destination = destination)) },
    )
}

@Composable
private fun MainScreenContent(
    currentState: MainState,
    navigate: (Destination) -> Unit,
) {
    if (currentState.destination is Destination.FullScreen) {
        Text("Full Screen TBD.")
        return
    }

    Column(modifier = Modifier.fillMaxSize()) {
        when (currentState.destination) {
            is Destination.NavBarScreen -> Column(modifier = Modifier.weight(1f)) {
                currentState.destination.Content()
            }
            is Destination.Dialog -> Text("Dialog TBD.")
            is Destination.FullScreen -> Unit // shouldn't ever happen
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
    navigate: (Destination) -> Unit,
) {
    val screens: List<Destination.NavBarScreen> = listOf(
        ThirdScreen,
        LiturgyScreen,
    )

    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        screens.forEach { screen ->
            NavigationBarItem(
                modifier = Modifier.weight(1f),
                selected = currentState.destination == screen,
                onClick = { navigate(screen) },
                icon = { Text(screen.title) }
            )
        }
    }
}
