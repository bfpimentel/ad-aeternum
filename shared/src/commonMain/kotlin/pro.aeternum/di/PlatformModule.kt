package pro.aeternum.di

import pro.aeternum.platform.FontFactory
import pro.aeternum.platform.Id
import pro.aeternum.platform.Threads

internal interface PlatformModule {
    val name: String
    val fontFactory: FontFactory
    val threads: Threads
}

internal class DefaultPlatformModule : PlatformModule {

    override val name: String by lazy { Id().get().value }
    override val fontFactory: FontFactory by lazy { FontFactory() }
    override val threads: Threads by lazy { Threads() }
}
