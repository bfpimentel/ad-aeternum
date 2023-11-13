package pro.aeternum.presentation.screens.thirdlist

import androidx.compose.foundation.BorderStroke
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
import pro.aeternum.presentation.screens.thirdlist.state.ThirdListActions
import pro.aeternum.presentation.screens.thirdlist.state.ThirdListState
import pro.aeternum.presentation.state.Store
import pro.aeternum.presentation.state.transientComposableStore

internal object ThirdListScreen : Destination.NavBarScreen {

    override val id: String = "third_list"
    override val title: String by lazy { strings.thirdsList.title }

    @Composable
    override fun Content() {
        val coroutineScope = rememberCoroutineScope()
        val store: Store<ThirdListState, ThirdListActions> = transientComposableStore {
            component.presentationModule.provideThirdsListStore(
                coroutineScope = coroutineScope
            )
        }
        val currentState by store.state.collectAsState()

        LaunchedEffect(true) { store.dispatch(ThirdListActions.Load) }

        ThirdsListScreenContent(
            currentState = currentState,
            navigateToThird = { id -> store.dispatch(ThirdListActions.SelectThird(id = id)) }
        )
    }
}

@Composable
private fun ThirdsListScreenContent(
    currentState: ThirdListState,
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
    currentState: ThirdListState,
    navigateToThird: (String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        AdAeternumAppBar()

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(currentState.thirds) { item ->
                ThirdItem(
                    item = item,
                    navigateToThird = navigateToThird,
                )
            }
        }
    }
}

@Composable
private fun ThirdItem(
    item: ThirdListState.Third,
    navigateToThird: (String) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        color = MaterialTheme.colorScheme.onPrimary,
        border = BorderStroke(
            width = 0.5.dp,
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
        ),
        onClick = { navigateToThird(item.id) },
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 12.dp,
                    bottom = if (item.subtitle == null) 12.dp else 0.dp,
                ),
                text = item.title,
                style = MaterialTheme.typography.bodyLarge
            )

            item.subtitle?.let { subtitle ->
                Text(
                    modifier = Modifier.padding(
                        start = 12.dp,
                        end = 12.dp,
                        top = 4.dp,
                        bottom = 12.dp,
                    ),
                    text = subtitle,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}
