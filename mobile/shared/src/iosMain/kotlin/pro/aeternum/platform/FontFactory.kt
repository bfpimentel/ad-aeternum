package pro.aeternum.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource

actual class FontFactory actual constructor() {

    private val cache: MutableMap<String, Font> = mutableMapOf()

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    actual fun createFont(name: String, res: String, weight: FontWeight, style: FontStyle): Font {
        return cache.getOrPut(res) {
            val byteArray = runBlocking {
                resource("font/$res.otf").readBytes()
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
