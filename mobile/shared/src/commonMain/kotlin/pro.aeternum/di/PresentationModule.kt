package pro.aeternum.di

import kotlin.properties.Delegates
import kotlinx.coroutines.CoroutineScope
import pro.aeternum.presentation.i18n.BrazilianPortugueseStrings
import pro.aeternum.presentation.i18n.I18nStrings
import pro.aeternum.presentation.navigation.Navigator
import pro.aeternum.presentation.screens.liturgy.state.LiturgyActions
import pro.aeternum.presentation.screens.liturgy.state.LiturgyReducer
import pro.aeternum.presentation.screens.liturgy.state.LiturgySideEffects
import pro.aeternum.presentation.screens.liturgy.state.LiturgyState
import pro.aeternum.presentation.screens.main.state.MainActions
import pro.aeternum.presentation.screens.main.state.MainReducer
import pro.aeternum.presentation.screens.main.state.MainState
import pro.aeternum.presentation.screens.third.state.ThirdActions
import pro.aeternum.presentation.screens.third.state.ThirdReducer
import pro.aeternum.presentation.screens.third.state.ThirdSideEffects
import pro.aeternum.presentation.screens.third.state.ThirdState
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

    fun provideThirdStore(
        coroutineScope: CoroutineScope,
    ): Store<ThirdState, ThirdActions>

    fun provideLiturgyStore(
        coroutineScope: CoroutineScope,
    ): Store<LiturgyState, LiturgyActions>
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

    override fun provideThirdStore(
        coroutineScope: CoroutineScope,
    ): Store<ThirdState, ThirdActions> = Store(
        coroutineScope = coroutineScope,
        initialState = ThirdState.INITIAL,
        reducer = ThirdReducer(),
        sideEffects = listOf(
            ThirdSideEffects(
                getThird = domainModule.provideGetThirdUseCase(),
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
}
