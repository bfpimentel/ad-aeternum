package pro.aeternum.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import pro.aeternum.di.strings

@Composable
internal fun AdAeternumErrorScreen(
    modifier: Modifier = Modifier,
    errorMessage: String,
    retry: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(0.5f),
            contentScale = ContentScale.FillHeight,
            painter = painterResource("drawable/aa_logo_light.xml"),
            contentDescription = null,
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            text = errorMessage
        )

        ElevatedButton(
            modifier = Modifier.padding(top = 32.dp),
            onClick = retry,
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
                text = strings.error.tryAgain.uppercase(),
            )
        }
    }
}
