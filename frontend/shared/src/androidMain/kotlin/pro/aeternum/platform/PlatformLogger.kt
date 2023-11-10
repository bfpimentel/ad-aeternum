package pro.aeternum.platform

import android.util.Log

actual class PlatformLogger actual constructor() {

    actual fun log(text: String) {
        Log.d("BRUNO", text)
    }
}
