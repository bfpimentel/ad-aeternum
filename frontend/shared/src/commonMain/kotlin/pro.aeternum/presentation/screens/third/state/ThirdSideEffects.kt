package pro.aeternum.presentation.screens.third.state

import pro.aeternum.di.component
import pro.aeternum.presentation.state.SideEffect

internal class ThirdSideEffects {

    fun get(): SideEffect<ThirdState, ThirdActions> = { state, action ->
        when (action) {
            is ThirdActions.Load -> try {
                this(
                    ThirdActions.SetThird(
                        text = component.dataModule.provideThirdsRepository().getThirds().title
                    )
                )
            } catch (exception: Exception) {
                print(exception)
            }
            else -> Unit
        }
    }
}
