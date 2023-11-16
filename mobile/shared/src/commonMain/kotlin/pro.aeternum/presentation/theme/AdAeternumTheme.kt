package pro.aeternum.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import pro.aeternum.di.component
import pro.aeternum.platform.FontFactory

@Composable
internal fun AdAeternumTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = createTypography(),
//        colorScheme = if (!isSystemInDarkTheme()) LightColors else DarkColors,
        colorScheme = LightColors,
        content = content,
    )
}

@Composable
private fun createButlerFontFamily(
    fontFactory: FontFactory = component.platformModule.fontFactory,
): FontFamily = FontFamily(
    fontFactory.createFont(
        name = "butler",
        res = "butler_ultra_light",
        weight = FontWeight.ExtraLight,
        style = FontStyle.Normal,
    ),
    fontFactory.createFont(
        name = "butler",
        res = "butler_light",
        weight = FontWeight.Light,
        style = FontStyle.Normal,
    ),
    fontFactory.createFont(
        name = "butler",
        res = "butler_regular",
        weight = FontWeight.Normal,
        style = FontStyle.Normal,
    ),
    fontFactory.createFont(
        name = "butler",
        res = "butler_bold",
        weight = FontWeight.Bold,
        style = FontStyle.Normal,
    ),
)

@Composable
private fun createTypography(): Typography {
    val butlerFontFamily = createButlerFontFamily()

    return MaterialTheme.typography.copy(
        displayLarge = MaterialTheme.typography.displayLarge.copy(
            fontFamily = butlerFontFamily,
            fontWeight = FontWeight.Bold,
        ),
        displayMedium = MaterialTheme.typography.displayMedium.copy(
            fontFamily = butlerFontFamily,
            fontWeight = FontWeight.Bold,
        ),
        displaySmall = MaterialTheme.typography.displaySmall.copy(
            fontFamily = butlerFontFamily,
            fontWeight = FontWeight.Bold,
        ),
        headlineLarge = MaterialTheme.typography.headlineLarge.copy(
            fontFamily = butlerFontFamily,
            fontWeight = FontWeight.Bold,
        ),
        headlineMedium = MaterialTheme.typography.headlineMedium.copy(
            fontFamily = butlerFontFamily,
            fontWeight = FontWeight.Bold,
        ),
        headlineSmall = MaterialTheme.typography.headlineSmall.copy(
            fontFamily = butlerFontFamily,
            fontWeight = FontWeight.Bold,
        ),
        titleLarge = MaterialTheme.typography.titleLarge.copy(
            fontFamily = butlerFontFamily,
            fontStyle = FontStyle.Normal,
        ),
        titleMedium = MaterialTheme.typography.titleMedium.copy(
            fontFamily = butlerFontFamily,
            fontStyle = FontStyle.Normal,
        ),
        titleSmall = MaterialTheme.typography.titleSmall.copy(
            fontFamily = butlerFontFamily,
            fontStyle = FontStyle.Normal,
        ),
        bodyLarge = MaterialTheme.typography.bodyLarge,
        bodyMedium = MaterialTheme.typography.bodyMedium,
        bodySmall = MaterialTheme.typography.bodySmall,
        labelLarge = MaterialTheme.typography.labelLarge.copy(
            fontFamily = butlerFontFamily,
            fontWeight = FontWeight.Light,
            fontStyle = FontStyle.Normal,
        ),
        labelMedium = MaterialTheme.typography.labelMedium.copy(
            fontFamily = butlerFontFamily,
            fontWeight = FontWeight.Light,
            fontStyle = FontStyle.Normal,
        ),
        labelSmall = MaterialTheme.typography.labelSmall.copy(
            fontFamily = butlerFontFamily,
            fontWeight = FontWeight.Light,
            fontStyle = FontStyle.Normal,
        ),
    )
}


