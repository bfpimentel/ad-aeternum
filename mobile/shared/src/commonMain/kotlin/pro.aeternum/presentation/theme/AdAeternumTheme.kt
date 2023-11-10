package pro.aeternum.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import pro.aeternum.di.component
import pro.aeternum.platform.FontFactory

@Composable
internal fun AdAeternumTheme(content: @Composable () -> Unit) {
    MaterialTheme(typography = createTypography(), content = content)
}

@Composable
private fun createLiberationFontFamily(
    fontFactory: FontFactory = component.platformModule.fontFactory,
): FontFamily = FontFamily(
    fontFactory.createFont(
        name = "liberation_serif",
        res = "liberation_serif_regular",
        weight = FontWeight.Normal,
        style = FontStyle.Normal,
    ),
    fontFactory.createFont(
        name = "liberation_serif",
        res = "liberation_serif_italic",
        weight = FontWeight.Normal,
        style = FontStyle.Italic,
    ),
    fontFactory.createFont(
        name = "liberation_serif",
        res = "liberation_serif_bold",
        weight = FontWeight.Bold,
        style = FontStyle.Normal,
    ),
    fontFactory.createFont(
        name = "liberation_serif",
        res = "liberation_serif_bold_italic",
        weight = FontWeight.Bold,
        style = FontStyle.Italic,
    ),
)

@Composable
private fun createTypography(): Typography {
    val liberationFontFamily = createLiberationFontFamily()

    return MaterialTheme.typography.copy(
        displayLarge = MaterialTheme.typography.displayLarge.copy(
            fontFamily = liberationFontFamily,
            fontWeight = FontWeight.Bold,
        ),
        displayMedium = MaterialTheme.typography.displayMedium.copy(
            fontFamily = liberationFontFamily,
            fontWeight = FontWeight.Bold,
        ),
        displaySmall = MaterialTheme.typography.displaySmall.copy(
            fontFamily = liberationFontFamily,
            fontWeight = FontWeight.Bold,
        ),
        headlineLarge = MaterialTheme.typography.headlineLarge.copy(
            fontFamily = liberationFontFamily,
            fontWeight = FontWeight.Bold,
        ),
        headlineMedium = MaterialTheme.typography.headlineMedium.copy(
            fontFamily = liberationFontFamily,
            fontWeight = FontWeight.Bold,
        ),
        headlineSmall = MaterialTheme.typography.headlineSmall.copy(
            fontFamily = liberationFontFamily,
            fontWeight = FontWeight.Bold,
        ),
        titleLarge = MaterialTheme.typography.titleLarge.copy(
            fontFamily = liberationFontFamily,
            fontStyle = FontStyle.Italic,
        ),
        titleMedium = MaterialTheme.typography.titleMedium.copy(
            fontFamily = liberationFontFamily,
            fontStyle = FontStyle.Italic,
        ),
        titleSmall = MaterialTheme.typography.titleSmall.copy(
            fontFamily = liberationFontFamily,
            fontStyle = FontStyle.Italic,
        ),
        bodyLarge = MaterialTheme.typography.bodyLarge,
        bodyMedium = MaterialTheme.typography.bodyMedium,
        bodySmall = MaterialTheme.typography.bodySmall,
        labelLarge = MaterialTheme.typography.labelLarge,
        labelMedium = MaterialTheme.typography.labelMedium,
        labelSmall = MaterialTheme.typography.labelSmall,
    )
}


