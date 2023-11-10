package pro.aeternum.presentation.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
internal inline fun <reified State, Action> composableStore(
    initialAction: Action? = null,
    crossinline init: (State?) -> Store<State, Action>,
): Store<State, Action> = rememberSaveable(
    saver = Saver(
        save = { store -> store.state.value as Any },
        restore = { restoredState -> init(restoredState as State) }
    ),
    init = {
        init(null).also { store ->
            if (initialAction != null) {
                store.dispatch(initialAction)
            }
        }
    }
)

@Composable
internal fun <State, Action> transientComposableStore(
    init: () -> Store<State, Action>,
): Store<State, Action> = remember { init() }
