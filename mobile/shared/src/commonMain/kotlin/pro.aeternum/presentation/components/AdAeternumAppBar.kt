package pro.aeternum.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import pro.aeternum.di.strings

@Composable
internal fun AdAeternumAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier.fillMaxWidth().background(Color.Transparent),
        title = {
            Text(
                text = strings.main.title,
                style = MaterialTheme.typography.displayMedium,
            )
        }
    )
}
