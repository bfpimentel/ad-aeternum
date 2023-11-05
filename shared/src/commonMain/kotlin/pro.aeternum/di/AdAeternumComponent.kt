package pro.aeternum.di

import pro.aeternum.platform.Font
import pro.aeternum.platform.Platform
import pro.aeternum.platform.Threads
import kotlin.properties.Delegates

internal var component: AdAeternumComponent by Delegates.notNull()

internal interface AdAeternumComponent {
    val platform: Platform
    val font: Font
    val threads: Threads
}

internal class DefaultAdAeternumComponent(
    override val platform: Platform,
    override val font: Font,
    override val threads: Threads,
) : AdAeternumComponent

fun startDI() {
    component = DefaultAdAeternumComponent(
        platform = Platform(),
        font = Font(),
        threads = Threads(),
    )
}
