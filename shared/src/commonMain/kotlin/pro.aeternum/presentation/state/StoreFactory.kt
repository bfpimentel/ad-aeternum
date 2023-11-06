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
        restore = { parcelable -> init(parcelable as State) }
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
internal inline fun <reified State, Action> transientComposableStore(
    store: Store<State, Action>,
): Store<State, Action> = remember { store }
