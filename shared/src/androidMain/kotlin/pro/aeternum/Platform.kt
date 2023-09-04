package pro.aeternum

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class Platform actual constructor() {
    actual val ioDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO

    actual fun getPlatformName(): String = "Android"
}