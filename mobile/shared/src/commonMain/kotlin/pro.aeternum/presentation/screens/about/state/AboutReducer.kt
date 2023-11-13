package pro.aeternum.presentation.screens.about.state

import pro.aeternum.presentation.state.Reducer

internal object AboutReducer {

    operator fun invoke(): Reducer<AboutState, AboutActions> = { state, _ -> state }
}
