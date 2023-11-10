package pro.aeternum.presentation.screens.liturgy

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
import pro.aeternum.presentation.screens.liturgy.state.LiturgyActions
import pro.aeternum.presentation.screens.liturgy.state.LiturgyState
import pro.aeternum.presentation.state.Store
import pro.aeternum.presentation.state.transientComposableStore

internal data object LiturgyScreen : AdAeternumDestination.NavBarScreen {

    override val title: String by lazy { strings.liturgy.title }

    @Composable
    override fun Content(navigate: (AdAeternumDestination) -> Unit) {
        val coroutineScope = rememberCoroutineScope()
        val store: Store<LiturgyState, LiturgyActions> = transientComposableStore {
            component.presentationModule.provideLiturgyStore(
                coroutineScope = coroutineScope
            )
        }
        val currentState by store.state.collectAsState()

        LaunchedEffect(true) { store.dispatch(LiturgyActions.Load) }

        LiturgyScreenContent(currentState = currentState)
    }
}

@Composable
private fun LiturgyScreenContent(currentState: LiturgyState) {
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
