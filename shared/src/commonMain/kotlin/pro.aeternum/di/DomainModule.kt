package pro.aeternum.di

internal interface DomainModule

internal class DefaultDomainModule(
    private val dataModule: DataModule,
) : DomainModule
