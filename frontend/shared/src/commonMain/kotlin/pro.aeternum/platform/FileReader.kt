package pro.aeternum.platform

expect class FileReader() {

    fun read(fileName: String): String?
}
