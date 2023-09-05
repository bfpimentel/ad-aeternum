package pro.aeternum

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import pro.aeternum.di.component
import pro.aeternum.presentation.theme.AdAeternumTheme

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AdAeternumApp() {
    AdAeternumTheme {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text(
                text = "Ad Aeternum",
                style = MaterialTheme.typography.displayMedium,
            )

            Text(
                text = "Ad Aeternum",
                style = MaterialTheme.typography.displayMedium,
                fontStyle = FontStyle.Italic,
            )

            Text(
                text = component.platform.getPlatformName(),
                style = MaterialTheme.typography.bodyLarge,
            )

            Text(
                text = component.platform.getPlatformName(),
                style = MaterialTheme.typography.bodyLarge,
                fontStyle = FontStyle.Italic,
            )
        }
    }
}
