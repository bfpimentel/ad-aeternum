package pro.aeternum.platform

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class Threads actual constructor() {

    actual val ioDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO
}