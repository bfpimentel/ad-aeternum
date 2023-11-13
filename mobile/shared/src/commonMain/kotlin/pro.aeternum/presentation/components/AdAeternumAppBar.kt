package pro.aeternum.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import pro.aeternum.di.strings

@Composable
internal fun AdAeternumAppBar(
    modifier: Modifier = Modifier,
    title: (@Composable () -> Unit),
    onNavigationIconClick: (() -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            titleContentColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
            actionIconContentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.onPrimary,
        ),
        title = { title() },
        navigationIcon = {
            if (onNavigationIconClick != null) {
                Icon(
                    modifier = Modifier
                        .clickable { onNavigationIconClick() }
                        .padding(all = 16.dp),
                    painter = painterResource("drawable/arrow_left.xml"),
                    contentDescription = null,
                )
            }
        },
        actions = { actions?.invoke(this) }
    )
}

@Composable
internal fun AdAeternumAppBar(
    modifier: Modifier = Modifier,
    showTitle: Boolean = true,
    onNavigationIconClick: (() -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null,
) {
    AdAeternumAppBar(
        modifier = modifier,
        title = {
            if (showTitle) {
                Text(
                    text = strings.main.title,
                    style = MaterialTheme.typography.displaySmall,
                )
            }
        },
        onNavigationIconClick = onNavigationIconClick,
        actions = { actions?.invoke(this) }
    )
}
