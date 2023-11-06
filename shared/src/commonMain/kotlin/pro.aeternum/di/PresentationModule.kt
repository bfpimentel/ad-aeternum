package pro.aeternum.di

import kotlinx.coroutines.CoroutineScope
import pro.aeternum.presentation.screens.liturgy.state.LiturgyActions
import pro.aeternum.presentation.screens.liturgy.state.LiturgyReducer
import pro.aeternum.presentation.screens.liturgy.state.LiturgySideEffects
import pro.aeternum.presentation.screens.liturgy.state.LiturgyState
import pro.aeternum.presentation.state.Store

internal interface PresentationModule {

    fun provideLiturgyStore(coroutineScope: CoroutineScope): Store<LiturgyState, LiturgyActions>
}

internal class DefaultPresentationModule(
    private val domainModule: DomainModule,
) : PresentationModule {

    override fun provideLiturgyStore(
        coroutineScope: CoroutineScope,
    ): Store<LiturgyState, LiturgyActions> = Store(
        coroutineScope = coroutineScope,
        initialState = LiturgyState.INITIAL,
        reducer = LiturgyReducer(),
        sideEffects = listOf(LiturgySideEffects(getLiturgy = domainModule.provideGetLiturgyUseCase()))
    )
}
