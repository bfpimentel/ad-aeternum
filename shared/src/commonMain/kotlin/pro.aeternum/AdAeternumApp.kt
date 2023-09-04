package pro.aeternum

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import pro.aeternum.di.component

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AdAeternumApp() {
    MaterialTheme {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text(
                text = "Ad Aeternum",
                style = MaterialTheme.typography.h2,
            )

            Text(
                text = component.platform.getPlatformName(),
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}