package pro.aeternum.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import pro.aeternum.di.component

@Composable
internal fun AdAeternumTheme(content: @Composable () -> Unit) {
    val liberationRegular = FontFamily(
        component.platform.font(
            name = "LiberationSerif",
            res = "LiberationSerif-Regular",
            weight = FontWeight.Normal,
            style = FontStyle.Normal,
        ),
        component.platform.font(
            name = "LiberationSerif",
            res = "LiberationSerif-Italic",
            weight = FontWeight.Normal,
            style = FontStyle.Italic,
        )
    )

    val liberationBold = FontFamily(
        component.platform.font(
            name = "LiberationSerif",
            res = "LiberationSerif-Bold",
            weight = FontWeight.Bold,
            style = FontStyle.Normal,
        ),
        component.platform.font(
            name = "LiberationSerif",
            res = "LiberationSerif-BoldItalic",
            weight = FontWeight.Bold,
            style = FontStyle.Italic,
        ),
    )

    val typography = MaterialTheme.typography.copy(
        displayLarge = MaterialTheme.typography.displayLarge.copy(fontFamily = liberationBold),
        displayMedium = MaterialTheme.typography.displayMedium.copy(fontFamily = liberationBold),
        displaySmall = MaterialTheme.typography.displaySmall.copy(fontFamily = liberationBold),
        headlineLarge = MaterialTheme.typography.headlineLarge,
        headlineMedium = MaterialTheme.typography.headlineMedium,
        headlineSmall = MaterialTheme.typography.headlineSmall,
        titleLarge = MaterialTheme.typography.titleLarge,
        titleMedium = MaterialTheme.typography.titleMedium,
        titleSmall = MaterialTheme.typography.titleSmall,
        bodyLarge = MaterialTheme.typography.bodyLarge.copy(fontFamily = liberationRegular),
        bodyMedium = MaterialTheme.typography.bodyMedium.copy(fontFamily = liberationRegular),
        bodySmall = MaterialTheme.typography.bodySmall.copy(fontFamily = liberationRegular),
        labelLarge = MaterialTheme.typography.labelLarge,
        labelMedium = MaterialTheme.typography.labelMedium,
        labelSmall = MaterialTheme.typography.labelSmall,
    )

    MaterialTheme(typography = typography, content = content)
}


