package pro.aeternum.platform

import java.io.InputStreamReader

actual class FileReader actual constructor() {

    actual fun read(fileName: String): String? {
        return javaClass.classLoader?.getResourceAsStream(fileName).use { stream ->
            InputStreamReader(stream).use { reader -> reader.readText() }
        }
    }
}
