package pro.aeternum.presentation.state

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope

internal class JsonSaver<Original, Saveable : Any> : Saver<Original, Saveable> {

    override fun restore(value: Saveable): Original? {
        TODO("Not yet implemented")
    }

    override fun SaverScope.save(value: Original): Saveable? {
        TODO("Not yet implemented")
    }
}
