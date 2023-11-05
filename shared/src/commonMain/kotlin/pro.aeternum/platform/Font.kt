package pro.aeternum.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

expect class Font() {
    @Composable
    fun getFont(name: String, res: String, weight: FontWeight, style: FontStyle): Font
}
