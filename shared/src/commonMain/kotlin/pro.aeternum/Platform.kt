package pro.aeternum

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.CoroutineDispatcher

expect class Platform() {
    val ioDispatcher: CoroutineDispatcher

    fun getPlatformName(): String

    @Composable
    fun font(name: String, res: String, weight: FontWeight, style: FontStyle): Font
}
