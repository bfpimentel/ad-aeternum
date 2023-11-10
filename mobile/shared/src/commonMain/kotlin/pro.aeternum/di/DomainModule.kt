package pro.aeternum.di

import pro.aeternum.domain.usecase.GetLiturgyUseCase

internal interface DomainModule {

    fun provideGetLiturgyUseCase(): GetLiturgyUseCase
}

internal class DefaultDomainModule(
    private val dataModule: DataModule,
) : DomainModule {

    override fun provideGetLiturgyUseCase(): GetLiturgyUseCase = GetLiturgyUseCase(
        liturgyRepository = dataModule.provideLiturgyRepository()
    )
}
