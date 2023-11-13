package pro.aeternum.presentation.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import pro.aeternum.di.component
import pro.aeternum.di.strings
import pro.aeternum.presentation.navigation.Destination
import pro.aeternum.presentation.screens.about.state.AboutActions
import pro.aeternum.presentation.screens.about.state.AboutState
import pro.aeternum.presentation.state.Store
import pro.aeternum.presentation.state.transientComposableStore

internal object AboutScreen : Destination.NavBarScreen {

    override val id: String = "about"
    override val title: String by lazy { strings.about.title }

    @Composable
    override fun Content() {
        val coroutineScope = rememberCoroutineScope()
        val store: Store<AboutState, AboutActions> = transientComposableStore {
            component.presentationModule.provideAboutStore(
                coroutineScope = coroutineScope
            )
        }
        val currentState by store.state.collectAsState()

        AboutScreenContent()
    }
}

@Composable
private fun AboutScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.7f),
            painter = painterResource("drawable/aa_logo_light.xml"),
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            strings.about.paragraphs.forEach { paragraph ->
                Text(
                    modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
                    text = paragraph,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}
