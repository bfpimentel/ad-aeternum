package pro.aeternum.presentation.state

internal typealias Reducer<State, Action> = (state: State, action: Action) -> State

internal typealias SideEffect<State, Action> = suspend Dispatch<Action>.(state: State, action: Action) -> Unit

internal typealias Dispatch<Action> = (Action) -> Unit
