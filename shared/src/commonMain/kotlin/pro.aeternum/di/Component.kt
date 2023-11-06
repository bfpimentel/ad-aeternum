package pro.aeternum.di

import kotlin.properties.Delegates

internal var component: Component by Delegates.notNull()

internal interface Component {
    val platformModule: PlatformModule
    val dataModule: DataModule
    val domainModule: DomainModule
    val presentationModule: PresentationModule
}

internal class DefaultComponent(
    override val platformModule: PlatformModule,
    override val dataModule: DataModule,
    override val domainModule: DomainModule,
    override val presentationModule: PresentationModule,
) : Component

fun initDI() {
    val platformModule: PlatformModule = DefaultPlatformModule()
    val dataModule: DataModule = DefaultDataModule()
    val domainModule: DomainModule = DefaultDomainModule(dataModule = dataModule)
    val presentationModule: PresentationModule = DefaultPresentationModule(domainModule = domainModule)


    component = DefaultComponent(
        platformModule = platformModule,
        dataModule = dataModule,
        domainModule = domainModule,
        presentationModule = presentationModule,
    )
}
