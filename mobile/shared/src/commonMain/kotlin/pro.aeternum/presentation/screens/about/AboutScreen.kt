package pro.aeternum.presentation.screens.about

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
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
            modifier = Modifier.fillMaxWidth(0.8f),
            painter = painterResource("drawable/aa_logo_light.xml"),
            contentScale = ContentScale.FillHeight,
            contentDescription = null,
        )

        mapOf(
//            strings.about.storyParagraphs to strings.about.storyParagraphs,
            strings.about.goalsTitle to strings.about.goalsParagraphs,
            strings.about.developmentTitle to strings.about.developmentParagraphs,
        ).forEach { (title, paragraphs) ->
            Section(
                title = title,
                paragraphs = paragraphs
            )
        }

        Text(
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.headlineSmall,
            text = strings.about.acknowledgementTitle,
        )

        strings.about.acknowledgementParagraphs.forEach { paragraph ->
            Text(
                modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                text = paragraph,
            )
        }
    }
}

@Composable
private fun Section(
    title: String,
    paragraphs: List<String>,
) {
    var isExpanded: Boolean by remember(paragraphs) { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        shape = RoundedCornerShape(size = 12.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        onClick = { isExpanded = !isExpanded }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )

            AnimatedVisibility(visible = isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    paragraphs.forEach { paragraph ->
                        Text(
                            modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyLarge,
                            text = paragraph,
                        )
                    }
                }
            }
        }
    }
}
