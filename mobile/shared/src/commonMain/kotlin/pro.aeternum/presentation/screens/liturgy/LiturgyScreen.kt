package pro.aeternum.presentation.screens.liturgy

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pro.aeternum.di.component
import pro.aeternum.di.strings
import pro.aeternum.presentation.components.AdAeternumAppBar
import pro.aeternum.presentation.components.AdAeternumProgressIndicator
import pro.aeternum.presentation.navigation.Destination
import pro.aeternum.presentation.screens.liturgy.state.LiturgyActions
import pro.aeternum.presentation.screens.liturgy.state.LiturgyState
import pro.aeternum.presentation.state.Store
import pro.aeternum.presentation.state.transientComposableStore

internal object LiturgyScreen : Destination.NavBarScreen {

    override val id: String = "liturgy"
    override val title: String by lazy { strings.liturgy.title }

    @Composable
    override fun Content() {
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
        currentState.isLoading -> AdAeternumProgressIndicator()
        else -> LiturgyScreenLoadedContent(currentState = currentState)
    }
}

@Composable
private fun LiturgyScreenLoadedContent(currentState: LiturgyState) {
    Column(modifier = Modifier.fillMaxWidth()) {
        AdAeternumAppBar()

        Text(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            text = strings.liturgy.inProgress,
        )

        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text(
                text = currentState.text,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}
