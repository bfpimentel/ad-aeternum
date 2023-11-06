package pro.aeternum.platform

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

actual class FontFactory actual constructor() {

    @SuppressLint("DiscouragedApi")
    @Composable
    actual fun createFont(name: String, res: String, weight: FontWeight, style: FontStyle): Font {
        val context = LocalContext.current
        val id = context.resources.getIdentifier(res, "font", context.packageName)
        return Font(resId = id, weight = weight, style = style)
    }
}
