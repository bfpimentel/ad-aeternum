package pro.aeternum

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource

actual class Platform actual constructor() {
    private val cache: MutableMap<String, Font> = mutableMapOf()

    actual val ioDispatcher: CoroutineDispatcher
        get() = Dispatchers.Default

    actual fun getPlatformName(): String = "iOS"

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    actual fun font(name: String, res: String, weight: FontWeight, style: FontStyle): Font {
        return cache.getOrPut(res) {
            val byteArray = runBlocking {
                resource("font/$res.ttf").readBytes()
            }
            androidx.compose.ui.text.platform.Font(
                identity = res,
                data = byteArray,
                weight = weight,
                style = style,
            )
        }
    }
}
