package pro.aeternum

import kotlinx.coroutines.CoroutineDispatcher

expect class Platform() {
    val ioDispatcher: CoroutineDispatcher
    fun getPlatformName(): String
}