package pro.aeternum.di

import pro.aeternum.platform.FileReader
import pro.aeternum.platform.Id
import pro.aeternum.platform.LocaleGetter
import pro.aeternum.platform.PlatformLogger
import pro.aeternum.platform.Threads

internal interface PlatformModule {
    val name: String
    val threads: Threads
    val localeGetter: LocaleGetter
    val logger: PlatformLogger
    val fileReader: FileReader
}

internal class DefaultPlatformModule : PlatformModule {

    override val name: String by lazy { Id().get().value }
    override val threads: Threads by lazy { Threads() }
    override val localeGetter: LocaleGetter by lazy { LocaleGetter() }
    override val logger: PlatformLogger by lazy { PlatformLogger() }
    override val fileReader: FileReader get() = FileReader()
}
