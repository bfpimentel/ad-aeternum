package pro.aeternum.presentation.screens.rosary

import ad_aeternum.shared.generated.resources.Res
import ad_aeternum.shared.generated.resources.arrow_left
import ad_aeternum.shared.generated.resources.arrow_right
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import pro.aeternum.di.component
import pro.aeternum.di.strings
import pro.aeternum.presentation.components.AdAeternumAppBar
import pro.aeternum.presentation.components.AdAeternumErrorScreen
import pro.aeternum.presentation.components.AdAeternumProgressIndicator
import pro.aeternum.presentation.navigation.Destination
import pro.aeternum.presentation.screens.rosary.state.RosaryActions
import pro.aeternum.presentation.screens.rosary.state.RosaryState
import pro.aeternum.presentation.state.Store
import pro.aeternum.presentation.state.transientComposableStore
import kotlin.properties.Delegates

internal object RosaryScreen : Destination.FullScreen {

    override val id: String = "rosary"
    override val title: String by lazy { strings.rosary.title }

    private var rosaryId: String by Delegates.notNull()

    fun create(rosaryId: String): RosaryScreen = RosaryScreen.apply {
        this.rosaryId = rosaryId
    }

    @Composable
    override fun Content() {
        val coroutineScope = rememberCoroutineScope()
        val store: Store<RosaryState, RosaryActions> = transientComposableStore {
            component.presentationModule.provideRosaryStore(
                coroutineScope = coroutineScope,
                rosaryId = rosaryId,
            )
        }
        val currentState by store.state.collectAsState()

        LaunchedEffect(true) { store.dispatch(RosaryActions.Load) }

        RosaryScreenContent(
            currentState = currentState,
            swipe = { index -> store.dispatch(RosaryActions.Swipe(index)) },
            navigateBack = { store.dispatch(RosaryActions.NavigateBack) },
            retry = { store.dispatch(RosaryActions.Load) }
        )
    }
}

@Composable
private fun RosaryScreenContent(
    currentState: RosaryState,
    swipe: (Int) -> Unit,
    navigateBack: () -> Unit,
    retry: () -> Unit,
) {
    when {
        currentState.hasError -> AdAeternumErrorScreen(
            errorMessage = strings.rosary.errorMessage,
            retry = retry
        )

        currentState.isLoading -> AdAeternumProgressIndicator()
        else -> RosaryScreenLoadedContent(
            currentState = currentState,
            swipe = swipe,
            navigateBack = navigateBack,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RosaryScreenLoadedContent(
    currentState: RosaryState,
    navigateBack: () -> Unit,
    swipe: (Int) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState { currentState.prayers.size }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { index -> swipe(index) }
    }

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

            HorizontalPager(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.Top,
                state = pagerState,
            ) { index ->
                Prayer(
                    modifier = Modifier.fillMaxSize(),
                    currentPrayer = currentState.prayers[index]
                )
            }

            PrayerNavigation(
                currentState = currentState,
                navigateToNext = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                },
                navigateToPrevious = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                },
            )
        }
    }
}

@Composable
private fun Prayer(modifier: Modifier = Modifier, currentPrayer: RosaryState.Prayer) {
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
    currentState: RosaryState,
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
                painter = painterResource(Res.drawable.arrow_left),
                contentDescription = null,
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            val groups = currentState.groups
            CountProgressIndicator(
                count = groups.size,
                selectedIndex = currentState.currentGroupIndex
            )

            Spacer(modifier = Modifier.padding(4.dp))

            val steps = groups[currentState.currentGroupIndex].prayers
            CountProgressIndicator(
                count = steps.size,
                selectedIndex = currentState.currentStepIndex
            )
        }

        FilledIconButton(
            modifier = Modifier.size(36.dp),
            shape = CircleShape,
            enabled = currentState.isNextEnabled,
            onClick = navigateToNext,
        ) {
            Icon(
                painter = painterResource(Res.drawable.arrow_right),
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
