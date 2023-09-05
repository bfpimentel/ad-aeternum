package pro.aeternum.di

import pro.aeternum.Platform
import kotlin.properties.Delegates

internal var component: AdAeternumComponent by Delegates.notNull()

internal interface AdAeternumComponent {
    val platform: Platform
}

internal class DefaultAdAeternumComponent(
    override val platform: Platform
) : AdAeternumComponent

fun startDI() {
    component = DefaultAdAeternumComponent(platform = Platform())
}
