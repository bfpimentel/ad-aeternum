package pro.aeternum.di

import pro.aeternum.domain.usecase.GetLiturgyUseCase
import pro.aeternum.domain.usecase.GetRosariesUseCase
import pro.aeternum.domain.usecase.GetSingleRosaryUseCase

internal interface DomainModule {
    fun provideGetRosariesUseCase(): GetRosariesUseCase
    fun provideGetSingleRosaryUseCase(): GetSingleRosaryUseCase
    fun provideGetLiturgyUseCase(): GetLiturgyUseCase
}

internal class DefaultDomainModule(
    private val dataModule: DataModule,
) : DomainModule {

    override fun provideGetRosariesUseCase(): GetRosariesUseCase = GetRosariesUseCase(
        rosariesRepository = dataModule.provideRosariesRepository(),
    )

    override fun provideGetSingleRosaryUseCase(): GetSingleRosaryUseCase = GetSingleRosaryUseCase(
        rosariesRepository = dataModule.provideRosariesRepository(),
    )

    override fun provideGetLiturgyUseCase(): GetLiturgyUseCase = GetLiturgyUseCase(
        liturgyRepository = dataModule.provideLiturgyRepository()
    )
}
