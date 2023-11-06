package pro.aeternum.presentation.screens.liturgy

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import pro.aeternum.di.component

@Composable
fun LiturgyScreen() {
    val coroutineScope = rememberCoroutineScope()
    var response by remember { mutableStateOf("not loaded") }

    LaunchedEffect(true) {
        coroutineScope.launch {
            response = try {
                component.dataModule.liturgyAPI.getLiturgy().day
            } catch (e: Exception) {
                e.message ?: "generic error"
            }
        }
    }

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Ad Aeternum",
            style = MaterialTheme.typography.displayMedium,
        )

        Text(
            text = response,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}


