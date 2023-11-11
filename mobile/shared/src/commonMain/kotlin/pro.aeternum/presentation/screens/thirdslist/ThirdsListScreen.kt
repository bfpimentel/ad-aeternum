package pro.aeternum.presentation.screens.thirdslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pro.aeternum.di.component
import pro.aeternum.di.strings
import pro.aeternum.presentation.components.AdAeternumAppBar
import pro.aeternum.presentation.components.AdAeternumProgressIndicator
import pro.aeternum.presentation.navigation.Destination
import pro.aeternum.presentation.screens.thirdslist.state.ThirdsListActions
import pro.aeternum.presentation.screens.thirdslist.state.ThirdsListState
import pro.aeternum.presentation.state.Store
import pro.aeternum.presentation.state.transientComposableStore

internal class ThirdsListScreen : Destination.NavBarScreen {

    override val id: String = "thirds_list"
    override val title: String by lazy { strings.thirdsList.title }

    @Composable
    override fun Content() {
        val coroutineScope = rememberCoroutineScope()
        val store: Store<ThirdsListState, ThirdsListActions> = transientComposableStore {
            component.presentationModule.provideThirdsListStore(
                coroutineScope = coroutineScope
            )
        }
        val currentState by store.state.collectAsState()

        LaunchedEffect(true) { store.dispatch(ThirdsListActions.Load) }

        ThirdsListScreenContent(
            currentState = currentState,
            navigateToThird = { id -> store.dispatch(ThirdsListActions.SelectThird(id = id)) }
        )
    }
}

@Composable
private fun ThirdsListScreenContent(
    currentState: ThirdsListState,
    navigateToThird: (String) -> Unit,
) {
    when {
        currentState.isLoading -> AdAeternumProgressIndicator()
        else -> ThirdsListLoadedScreenContent(
            currentState = currentState,
            navigateToThird = navigateToThird,
        )
    }
}

@Composable
private fun ThirdsListLoadedScreenContent(
    currentState: ThirdsListState,
    navigateToThird: (String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        AdAeternumAppBar()

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(currentState.thirds) { item ->
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navigateToThird(item.id) },
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
        }
    }
}
