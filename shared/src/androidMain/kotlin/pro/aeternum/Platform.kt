package pro.aeternum

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class Platform actual constructor() {
    actual val ioDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO

    actual fun getPlatformName(): String = "Android"

    @Composable
    actual fun font(name: String, res: String, weight: FontWeight, style: FontStyle): Font {
        val context = LocalContext.current
        val id = context.resources.getIdentifier(res, "font", context.packageName)
        return Font(resId = id, weight = weight, style = style)
    }
}
