package pro.aeternum.di

internal val component: AdAeternumComponent by lazy { createComponent() }

internal interface AdAeternumComponent {
    val platformModule: PlatformModule
    val dataModule: DataModule
    val domainModule: DomainModule
    val presentationModule: PresentationModule
}

internal class DefaultAdAeternumComponent(
    override val platformModule: PlatformModule,
    override val dataModule: DataModule,
    override val domainModule: DomainModule,
    override val presentationModule: PresentationModule,
) : AdAeternumComponent

private fun createComponent(): AdAeternumComponent {
    val platformModule: PlatformModule = DefaultPlatformModule()
    val dataModule: DataModule = DefaultDataModule(platformModule = platformModule)
    val domainModule: DomainModule = DefaultDomainModule(dataModule = dataModule)
    val presentationModule: PresentationModule = DefaultPresentationModule(
        domainModule = domainModule,
    )

    return DefaultAdAeternumComponent(
        platformModule = platformModule,
        dataModule = dataModule,
        domainModule = domainModule,
        presentationModule = presentationModule,
    )
}
