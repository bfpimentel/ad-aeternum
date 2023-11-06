package pro.aeternum.presentation.state

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class Store<State, Action>(
    coroutineScope: CoroutineScope,
    initialState: State,
    reducer: Reducer<State, Action>,
    sideEffects: List<SideEffect<State, Action>> = emptyList(),
) {

    private val dispatcher: Channel<Action> = Channel(Channel.UNLIMITED)

    val dispatch: Dispatch<Action> = { action -> dispatcher.trySend(action) }
    val state: StateFlow<State>

    init {
        state = dispatcher
            .receiveAsFlow()
            .scan(initialState) { state, action ->
                reducer(state, action).also { newState ->
                    sideEffects.forEach { sideEffect ->
                        coroutineScope.launch {
                            sideEffect.invoke(dispatch, newState, action)
                        }
                    }
                }
            }
            .stateIn(coroutineScope, SharingStarted.Eagerly, initialState)
    }
}
