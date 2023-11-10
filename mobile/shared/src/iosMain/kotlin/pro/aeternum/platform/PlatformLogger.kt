package pro.aeternum.platform

import platform.Foundation.NSLog

actual class PlatformLogger actual constructor() {

    actual fun log(text: String) {
        NSLog(text)
    }
}
