package pro.aeternum.platform

import kotlinx.coroutines.CoroutineDispatcher

expect class Threads() {
    val io: CoroutineDispatcher
}
