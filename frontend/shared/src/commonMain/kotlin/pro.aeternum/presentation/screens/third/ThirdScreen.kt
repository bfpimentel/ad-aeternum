package pro.aeternum.presentation.screens.third

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pro.aeternum.di.component
import pro.aeternum.di.strings
import pro.aeternum.presentation.navigation.AdAeternumDestination
import pro.aeternum.presentation.screens.third.state.ThirdActions
import pro.aeternum.presentation.screens.third.state.ThirdState
import pro.aeternum.presentation.state.Store
import pro.aeternum.presentation.state.transientComposableStore

internal data object ThirdScreen : AdAeternumDestination.NavBarScreen {

    override val title: String by lazy { strings.third.title }

    @Composable
    override fun Content(navigate: (AdAeternumDestination) -> Unit) {
        val coroutineScope = rememberCoroutineScope()
        val store: Store<ThirdState, ThirdActions> = transientComposableStore {
            component.presentationModule.provideThirdStore(
                coroutineScope = coroutineScope
            )
        }
        val currentState by store.state.collectAsState()

        LaunchedEffect(true) { store.dispatch(ThirdActions.Load) }

        ThirdScreenContent(currentState = currentState)
    }
}

@Composable
private fun ThirdScreenContent(currentState: ThirdState) {
    when {
        currentState.isLoading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
        else -> Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text(
                text = currentState.text,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}
