package pro.aeternum.di

import kotlinx.coroutines.CoroutineScope
import pro.aeternum.presentation.i18n.BrazilianPortugueseStrings
import pro.aeternum.presentation.i18n.EnglishStrings
import pro.aeternum.presentation.i18n.I18nStrings
import pro.aeternum.presentation.screens.liturgy.state.LiturgyActions
import pro.aeternum.presentation.screens.liturgy.state.LiturgyReducer
import pro.aeternum.presentation.screens.liturgy.state.LiturgySideEffects
import pro.aeternum.presentation.screens.liturgy.state.LiturgyState
import pro.aeternum.presentation.screens.main.state.MainActions
import pro.aeternum.presentation.screens.main.state.MainReducer
import pro.aeternum.presentation.screens.main.state.MainState
import pro.aeternum.presentation.state.Store

internal interface PresentationModule {

    fun provideStrings(): I18nStrings

    fun provideMainStore(coroutineScope: CoroutineScope, restoredState: MainState?): Store<MainState, MainActions>

    fun provideLiturgyStore(coroutineScope: CoroutineScope): Store<LiturgyState, LiturgyActions>
}

internal class DefaultPresentationModule(
    private val platformModule: PlatformModule,
    private val domainModule: DomainModule,
) : PresentationModule {

    override fun provideStrings(): I18nStrings {
        return if (platformModule.localeGetter.getLanguage().contains("pt")) {
            BrazilianPortugueseStrings()
        } else {
            EnglishStrings()
        }
    }

    override fun provideMainStore(
        coroutineScope: CoroutineScope,
        restoredState: MainState?,
    ): Store<MainState, MainActions> = Store(
        coroutineScope = coroutineScope,
        initialState = restoredState ?: MainState.INITIAL,
        reducer = MainReducer(),
        sideEffects = listOf()
    )

    override fun provideLiturgyStore(
        coroutineScope: CoroutineScope,
    ): Store<LiturgyState, LiturgyActions> = Store(
        coroutineScope = coroutineScope,
        initialState = LiturgyState.INITIAL,
        reducer = LiturgyReducer(),
        sideEffects = listOf(LiturgySideEffects(getLiturgy = domainModule.provideGetLiturgyUseCase()))
    )
}

internal val strings: I18nStrings by lazy {
    component.presentationModule.provideStrings()
}
