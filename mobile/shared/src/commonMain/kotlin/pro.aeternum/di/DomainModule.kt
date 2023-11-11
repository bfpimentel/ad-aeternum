package pro.aeternum.di

import pro.aeternum.domain.usecase.GetLiturgyUseCase
import pro.aeternum.domain.usecase.GetThirdUseCase
import pro.aeternum.domain.usecase.GetThirdsListUseCase

internal interface DomainModule {
    fun provideGetThirdsListUseCase(): GetThirdsListUseCase
    fun provideGetThirdUseCase(): GetThirdUseCase
    fun provideGetLiturgyUseCase(): GetLiturgyUseCase
}

internal class DefaultDomainModule(
    private val dataModule: DataModule,
) : DomainModule {

    override fun provideGetThirdsListUseCase(): GetThirdsListUseCase = GetThirdsListUseCase(
        thirdsRepository = dataModule.provideThirdsRepository(),
    )

    override fun provideGetThirdUseCase(): GetThirdUseCase = GetThirdUseCase(
        thirdsRepository = dataModule.provideThirdsRepository(),
    )

    override fun provideGetLiturgyUseCase(): GetLiturgyUseCase = GetLiturgyUseCase(
        liturgyRepository = dataModule.provideLiturgyRepository()
    )
}
