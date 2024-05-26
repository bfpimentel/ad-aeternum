package pro.aeternum.di

import kotlin.properties.Delegates
import kotlinx.coroutines.CoroutineScope
import pro.aeternum.presentation.i18n.BrazilianPortugueseStrings
import pro.aeternum.presentation.i18n.I18nStrings
import pro.aeternum.presentation.navigation.Navigator
import pro.aeternum.presentation.screens.about.state.AboutActions
import pro.aeternum.presentation.screens.about.state.AboutReducer
import pro.aeternum.presentation.screens.about.state.AboutState
import pro.aeternum.presentation.screens.liturgy.state.LiturgyActions
import pro.aeternum.presentation.screens.liturgy.state.LiturgyReducer
import pro.aeternum.presentation.screens.liturgy.state.LiturgySideEffects
import pro.aeternum.presentation.screens.liturgy.state.LiturgyState
import pro.aeternum.presentation.screens.main.state.MainActions
import pro.aeternum.presentation.screens.main.state.MainReducer
import pro.aeternum.presentation.screens.main.state.MainState
import pro.aeternum.presentation.screens.rosary.state.RosaryActions
import pro.aeternum.presentation.screens.rosary.state.RosaryReducer
import pro.aeternum.presentation.screens.rosary.state.RosarySideEffects
import pro.aeternum.presentation.screens.rosary.state.RosaryState
import pro.aeternum.presentation.screens.rosarylist.state.RosariesListAction
import pro.aeternum.presentation.screens.rosarylist.state.RosariesListReducer
import pro.aeternum.presentation.screens.rosarylist.state.RosariesListSideEffects
import pro.aeternum.presentation.screens.rosarylist.state.RosariesListState
import pro.aeternum.presentation.state.Store

internal val strings: I18nStrings by lazy {
    component.presentationModule.provideStrings()
}

internal interface PresentationModule {

    fun provideStrings(): I18nStrings

    fun registerNavigator(navigator: Navigator)

    fun provideMainStore(
        coroutineScope: CoroutineScope,
        restoredState: MainState?,
    ): Store<MainState, MainActions>

    fun provideRosariesListStore(
        coroutineScope: CoroutineScope,
    ): Store<RosariesListState, RosariesListAction>

    fun provideRosaryStore(
        coroutineScope: CoroutineScope,
        rosaryId: String,
    ): Store<RosaryState, RosaryActions>

    fun provideLiturgyStore(
        coroutineScope: CoroutineScope,
    ): Store<LiturgyState, LiturgyActions>

    fun provideAboutStore(
        coroutineScope: CoroutineScope,
    ): Store<AboutState, AboutActions>
}

internal class DefaultPresentationModule(
    private val domainModule: DomainModule,
) : PresentationModule {

    private var navigator: Navigator by Delegates.notNull()

    override fun provideStrings(): I18nStrings {
        return BrazilianPortugueseStrings()
    }

    override fun registerNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    override fun provideMainStore(
        coroutineScope: CoroutineScope,
        restoredState: MainState?,
    ): Store<MainState, MainActions> = Store(
        coroutineScope = coroutineScope,
        initialState = restoredState ?: MainState.INITIAL,
        reducer = MainReducer(),
        sideEffects = listOf(),
    )

    override fun provideRosariesListStore(
        coroutineScope: CoroutineScope,
    ): Store<RosariesListState, RosariesListAction> = Store(
        coroutineScope = coroutineScope,
        initialState = RosariesListState.INITIAL,
        reducer = RosariesListReducer(),
        sideEffects = listOf(
            RosariesListSideEffects(
                getRosaries = domainModule.provideGetRosariesUseCase(),
                navigator = navigator,
            ).get()
        )
    )

    override fun provideRosaryStore(
        coroutineScope: CoroutineScope,
        rosaryId: String,
    ): Store<RosaryState, RosaryActions> = Store(
        coroutineScope = coroutineScope,
        initialState = RosaryState.INITIAL,
        reducer = RosaryReducer(),
        sideEffects = listOf(
            RosarySideEffects(
                rosaryId = rosaryId,
                getSingleRosary = domainModule.provideGetSingleRosaryUseCase(),
                navigator = navigator,
            ).get(),
        )
    )

    override fun provideLiturgyStore(
        coroutineScope: CoroutineScope,
    ): Store<LiturgyState, LiturgyActions> = Store(
        coroutineScope = coroutineScope,
        initialState = LiturgyState.INITIAL,
        reducer = LiturgyReducer(),
        sideEffects = listOf(
            LiturgySideEffects(
                getLiturgy = domainModule.provideGetLiturgyUseCase()
            ).get(),
        )
    )

    override fun provideAboutStore(
        coroutineScope: CoroutineScope,
    ): Store<AboutState, AboutActions> = Store(
        coroutineScope = coroutineScope,
        initialState = AboutState.INITIAL,
        reducer = AboutReducer(),
        sideEffects = listOf(),
    )
}
