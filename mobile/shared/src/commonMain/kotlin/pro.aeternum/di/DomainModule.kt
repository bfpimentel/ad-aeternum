package pro.aeternum.di

import pro.aeternum.domain.usecase.GetLiturgyUseCase
import pro.aeternum.domain.usecase.GetThirdUseCase

internal interface DomainModule {
    fun provideGetThirdUseCase(): GetThirdUseCase
    fun provideGetLiturgyUseCase(): GetLiturgyUseCase
}

internal class DefaultDomainModule(
    private val dataModule: DataModule,
) : DomainModule {

    override fun provideGetThirdUseCase(): GetThirdUseCase = GetThirdUseCase(
        thirdsRepository = dataModule.provideThirdsRepository(),
    )

    override fun provideGetLiturgyUseCase(): GetLiturgyUseCase = GetLiturgyUseCase(
        liturgyRepository = dataModule.provideLiturgyRepository()
    )
}
