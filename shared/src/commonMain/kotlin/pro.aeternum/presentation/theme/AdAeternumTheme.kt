package pro.aeternum.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import pro.aeternum.di.component

@Composable
internal fun AdAeternumTheme(content: @Composable () -> Unit) {
    MaterialTheme(typography = createTypography(), content = content)
}

@Composable
private fun createLiberationFontFamily(): FontFamily = FontFamily(
    component.font.getFont(
        name = "liberation_serif",
        res = "liberation_serif_regular",
        weight = FontWeight.Normal,
        style = FontStyle.Normal,
    ),
    component.font.getFont(
        name = "liberation_serif",
        res = "liberation_serif_italic",
        weight = FontWeight.Normal,
        style = FontStyle.Italic,
    ),
    component.font.getFont(
        name = "liberation_serif",
        res = "liberation_serif_bold",
        weight = FontWeight.Bold,
        style = FontStyle.Normal,
    ),
    component.font.getFont(
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
        headlineLarge = MaterialTheme.typography.headlineLarge,
        headlineMedium = MaterialTheme.typography.headlineMedium,
        headlineSmall = MaterialTheme.typography.headlineSmall,
        titleLarge = MaterialTheme.typography.titleLarge,
        titleMedium = MaterialTheme.typography.titleMedium,
        titleSmall = MaterialTheme.typography.titleSmall,
        bodyLarge = MaterialTheme.typography.bodyLarge.copy(
            fontFamily = liberationFontFamily,
            fontWeight = FontWeight.Normal,
        ),
        bodyMedium = MaterialTheme.typography.bodyMedium.copy(
            fontFamily = liberationFontFamily,
            fontWeight = FontWeight.Normal,
        ),
        bodySmall = MaterialTheme.typography.bodySmall.copy(
            fontFamily = liberationFontFamily,
            fontWeight = FontWeight.Normal,
        ),
        labelLarge = MaterialTheme.typography.labelLarge,
        labelMedium = MaterialTheme.typography.labelMedium,
        labelSmall = MaterialTheme.typography.labelSmall,
    )
}


