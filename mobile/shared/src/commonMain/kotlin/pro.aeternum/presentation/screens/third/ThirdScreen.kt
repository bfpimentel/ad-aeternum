package pro.aeternum.presentation.screens.third

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlin.properties.Delegates
import org.jetbrains.compose.resources.painterResource
import pro.aeternum.di.component
import pro.aeternum.di.strings
import pro.aeternum.presentation.components.AdAeternumAppBar
import pro.aeternum.presentation.components.AdAeternumProgressIndicator
import pro.aeternum.presentation.navigation.Destination
import pro.aeternum.presentation.screens.third.state.ThirdActions
import pro.aeternum.presentation.screens.third.state.ThirdState
import pro.aeternum.presentation.state.Store
import pro.aeternum.presentation.state.transientComposableStore

internal object ThirdScreen : Destination.FullScreen {

    override val id: String = "third"
    override val title: String by lazy { strings.third.title }

    private var thirdId: String by Delegates.notNull()

    fun create(thirdId: String): ThirdScreen = ThirdScreen.apply {
        this.thirdId = thirdId
    }

    @Composable
    override fun Content() {
        val coroutineScope = rememberCoroutineScope()
        val store: Store<ThirdState, ThirdActions> = transientComposableStore {
            component.presentationModule.provideThirdStore(
                coroutineScope = coroutineScope,
                thirdId = thirdId,
            )
        }
        val currentState by store.state.collectAsState()

        LaunchedEffect(true) { store.dispatch(ThirdActions.Load) }

        ThirdScreenContent(
            currentState = currentState,
            navigateToNext = { store.dispatch(ThirdActions.Next) },
            navigateToPrevious = { store.dispatch(ThirdActions.Previous) },
            navigateBack = { store.dispatch(ThirdActions.NavigateBack) },
        )
    }
}

@Composable
private fun ThirdScreenContent(
    currentState: ThirdState,
    navigateToNext: () -> Unit,
    navigateToPrevious: () -> Unit,
    navigateBack: () -> Unit,
) {
    when {
        currentState.isLoading -> AdAeternumProgressIndicator()
        else -> ThirdScreenLoadedContent(
            currentState = currentState,
            navigateToNext = navigateToNext,
            navigateToPrevious = navigateToPrevious,
            navigateBack = navigateBack,
        )
    }
}

@Composable
private fun ThirdScreenLoadedContent(
    currentState: ThirdState,
    navigateToNext: () -> Unit,
    navigateToPrevious: () -> Unit,
    navigateBack: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        AdAeternumAppBar(
            showTitle = false,
            onNavigationIconClick = navigateBack,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = currentState.title,
                style = MaterialTheme.typography.headlineMedium,
            )

            Prayer(modifier = Modifier.weight(1f), currentPrayer = currentState.prayer)

            PrayerNavigation(
                currentState = currentState,
                navigateToNext = navigateToNext,
                navigateToPrevious = navigateToPrevious,
            )
        }
    }
}

@Composable
private fun Prayer(modifier: Modifier = Modifier, currentPrayer: ThirdState.Prayer) {
    Column(modifier = modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {
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
private fun PrayerNavigation(
    currentState: ThirdState,
    navigateToNext: () -> Unit,
    navigateToPrevious: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        FilledIconButton(
            modifier = Modifier.size(36.dp),
            shape = CircleShape,
            enabled = currentState.isPreviousEnabled,
            onClick = navigateToPrevious,
        ) {
            Icon(
                painter = painterResource("drawable/arrow_left.xml"),
                contentDescription = null,
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            val groups = currentState.groups
            CountProgressIndicator(count = groups.size, selectedIndex = currentState.currentGroupIndex)

            Spacer(modifier = Modifier.padding(4.dp))

            val steps = groups[currentState.currentGroupIndex].prayers
            CountProgressIndicator(count = steps.size, selectedIndex = currentState.currentStepIndex)
        }

        FilledIconButton(
            modifier = Modifier.size(36.dp),
            shape = CircleShape,
            enabled = currentState.isNextEnabled,
            onClick = navigateToNext,
        ) {
            Icon(
                painter = painterResource("drawable/arrow_right.xml"),
                contentDescription = null,
            )
        }
    }
}

@Composable
private fun CountProgressIndicator(count: Int, selectedIndex: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        for (index in 0..<count) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(
                        if (index == selectedIndex) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.surfaceVariant
                        }
                    )
            )
        }
    }
}
