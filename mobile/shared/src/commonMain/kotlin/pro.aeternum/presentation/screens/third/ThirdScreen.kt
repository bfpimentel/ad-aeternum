package pro.aeternum.presentation.screens.third

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.MaterialTheme
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
import pro.aeternum.presentation.screens.third.state.ThirdActions
import pro.aeternum.presentation.screens.third.state.ThirdState
import pro.aeternum.presentation.state.Store
import pro.aeternum.presentation.state.transientComposableStore

internal data object ThirdScreen : Destination.NavBarScreen {

    override val title: String by lazy { strings.third.title }

    @Composable
    override fun Content() {
        val coroutineScope = rememberCoroutineScope()
        val store: Store<ThirdState, ThirdActions> = transientComposableStore {
            component.presentationModule.provideThirdStore(coroutineScope = coroutineScope)
        }
        val currentState by store.state.collectAsState()

        LaunchedEffect(true) { store.dispatch(ThirdActions.Load) }

        ThirdScreenContent(currentState = currentState)
    }
}

@Composable
private fun ThirdScreenContent(currentState: ThirdState) {
    when {
        currentState.isLoading -> AdAeternumProgressIndicator()
        else -> ThirdScreenLoadedContent(currentState = currentState)
    }
}

@Composable
private fun ThirdScreenLoadedContent(currentState: ThirdState) {
    Column(modifier = Modifier.fillMaxWidth()) {
        AdAeternumAppBar()

        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            ThirdTitle(title = currentState.title, subtitle = currentState.subtitle)
            Prayer(currentPrayer = currentState.prayer)
        }
    }
}

@Composable
private fun ThirdTitle(title: String, subtitle: String?) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
        )

        subtitle?.let { subtitle ->
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = subtitle,
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
}

@Composable
private fun Prayer(currentPrayer: ThirdState.Prayer) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = currentPrayer.title,
            style = MaterialTheme.typography.titleLarge,
        )

        currentPrayer.subtitle?.let { prayerSubtitle ->
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = prayerSubtitle,
                style = MaterialTheme.typography.titleMedium,
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        currentPrayer.paragraphs.forEach { paragraph ->
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = paragraph,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
private fun ProgressIndicator(currentState: ThirdState) {
    Row(modifier = Modifier.fillMaxWidth()) {
    }
}
