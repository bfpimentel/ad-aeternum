package pro.aeternum.di

internal interface PresentationModule

internal class DefaultPresentationModule(
    private val domainModule: DomainModule,
) : PresentationModule
