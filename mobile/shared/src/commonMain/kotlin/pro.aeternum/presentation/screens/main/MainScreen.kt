package pro.aeternum.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import pro.aeternum.di.component
import pro.aeternum.presentation.navigation.Destination
import pro.aeternum.presentation.navigation.Navigator
import pro.aeternum.presentation.screens.about.AboutScreen
import pro.aeternum.presentation.screens.liturgy.LiturgyScreen
import pro.aeternum.presentation.screens.main.state.MainActions
import pro.aeternum.presentation.screens.main.state.MainState
import pro.aeternum.presentation.screens.rosarylist.RosariesListScreen
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

    component.presentationModule.registerNavigator(object : Navigator {
        override fun navigate(destination: Destination) = store.dispatch(
            MainActions.Navigate(destination = destination)
        )

        override fun navigateBack() = store.dispatch(MainActions.NavigateBack)
    })

    MainScreenContent(
        currentState = currentState,
        navigate = { destination ->
            store.dispatch(MainActions.Navigate(destination = destination))
        },
    )
}

@Composable
private fun MainScreenContent(
    currentState: MainState,
    navigate: (Destination) -> Unit,
) {
    when (currentState.destination) {
        is Destination.FullScreen -> FullScreen(currentState = currentState)
        is Destination.NavBarScreen -> NavBarScreen(
            currentState = currentState,
            navigate = { destination -> navigate(destination) }
        )

        is Destination.Dialog -> Text("Dialog TBD.")
    }
}

@Composable
private fun FullScreen(currentState: MainState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {
        currentState.destination.Content()
    }
}

@Composable
private fun NavBarScreen(
    currentState: MainState,
    navigate: (Destination) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {
        Column(modifier = Modifier.weight(1f)) { currentState.destination.Content() }

        MainNavBar(currentState = currentState, navigate = navigate)
    }
}

@Preview
@Composable
private fun MainNavBar(
    currentState: MainState,
    navigate: (Destination) -> Unit,
) {
    val screens: List<Destination.NavBarScreen> = listOf(
        RosariesListScreen,
        LiturgyScreen,
        AboutScreen,
    )

    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        screens.forEach { screen ->
            NavigationBarItem(
                modifier = Modifier.weight(1f),
                selected = currentState.destination.id == screen.id,
                onClick = { navigate(screen) },
                icon = { Text(screen.title) }
            )
        }
    }
}
