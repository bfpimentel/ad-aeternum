package pro.aeternum.platform

expect class Id() {

    fun get(): PlatformId
}

enum class PlatformId(internal val value: String) {
    ANDROID(value = "Android"),
    IOS(value = "iOS"),
}

