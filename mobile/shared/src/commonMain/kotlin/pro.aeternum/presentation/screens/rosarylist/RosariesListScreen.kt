package pro.aeternum.presentation.screens.rosarylist

import ad_aeternum.shared.generated.resources.Res
import ad_aeternum.shared.generated.resources.arrow_right
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import pro.aeternum.di.component
import pro.aeternum.di.strings
import pro.aeternum.presentation.components.AdAeternumAppBar
import pro.aeternum.presentation.components.AdAeternumErrorScreen
import pro.aeternum.presentation.components.AdAeternumProgressIndicator
import pro.aeternum.presentation.navigation.Destination
import pro.aeternum.presentation.screens.rosarylist.state.RosariesListAction
import pro.aeternum.presentation.screens.rosarylist.state.RosariesListState
import pro.aeternum.presentation.state.Store
import pro.aeternum.presentation.state.transientComposableStore

internal object RosariesListScreen : Destination.NavBarScreen {

    override val id: String = "rosaries_list"
    override val title: String by lazy { strings.rosariesList.title }

    @Composable
    override fun Content() {
        val coroutineScope = rememberCoroutineScope()
        val store: Store<RosariesListState, RosariesListAction> = transientComposableStore {
            component.presentationModule.provideRosariesListStore(
                coroutineScope = coroutineScope
            )
        }
        val currentState by store.state.collectAsState()

        LaunchedEffect(true) { store.dispatch(RosariesListAction.Load) }

        RosariesListScreenContent(
            currentState = currentState,
            navigateToRosary = { id -> store.dispatch(RosariesListAction.SelectRosary(id = id)) },
            retry = { store.dispatch(RosariesListAction.Load) },
        )
    }
}

@Composable
private fun RosariesListScreenContent(
    currentState: RosariesListState,
    navigateToRosary: (String) -> Unit,
    retry: () -> Unit,
) {
    when {
        currentState.hasError -> AdAeternumErrorScreen(
            errorMessage = strings.rosariesList.errorMessage,
            retry = retry
        )
        currentState.isLoading -> AdAeternumProgressIndicator()
        else -> RosariesListLoadedScreenContent(
            currentState = currentState,
            navigateToRosary = navigateToRosary,
        )
    }
}

@Composable
private fun RosariesListLoadedScreenContent(
    currentState: RosariesListState,
    navigateToRosary: (String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        AdAeternumAppBar()

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(currentState.rosaries) { item ->
                RosaryItem(
                    item = item,
                    navigateToRosary = navigateToRosary,
                )

                HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
            }
        }
    }
}

@Composable
private fun RosaryItem(
    item: RosariesListState.Rosary,
    navigateToRosary: (String) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        color = MaterialTheme.colorScheme.onPrimary,
        onClick = { navigateToRosary(item.id) },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodyLarge
                )

                item.subtitle?.let { subtitle ->
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = subtitle,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

            Spacer(modifier = Modifier.padding(6.dp))

            Image(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                painter = painterResource(Res.drawable.arrow_right),
                contentDescription = null,
            )
        }
    }
}
