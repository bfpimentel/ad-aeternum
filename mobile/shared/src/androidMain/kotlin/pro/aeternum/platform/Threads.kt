package pro.aeternum.platform

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class Threads actual constructor() {

    actual val main: CoroutineDispatcher = Dispatchers.Main

    actual val io: CoroutineDispatcher = Dispatchers.IO
}
