package pro.aeternum.di

import kotlin.properties.Delegates

internal var component: AdAeternumComponent by Delegates.notNull()

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

fun startDI() {
    val platformModule: PlatformModule = DefaultPlatformModule()
    val dataModule: DataModule = DefaultDataModule()
    val domainModule: DomainModule = DefaultDomainModule(dataModule = dataModule)
    val presentationModule: PresentationModule = DefaultPresentationModule(domainModule = domainModule)


    component = DefaultAdAeternumComponent(
        platformModule = platformModule,
        dataModule = dataModule,
        domainModule = domainModule,
        presentationModule = presentationModule,
    )
}
