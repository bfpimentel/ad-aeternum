package pro.aeternum

object AdAeternumVersions {
    const val versionName = "1.0.1"
    val versionCode: Int get() = versionName.toVersionCode()

    private fun String.toVersionCode(): Int = split(".").takeIf { it.size == 3 }?.let { versionArray ->
        (versionArray[0].toInt() * 10000) + (versionArray[1].toInt() * 100) + versionArray[2].toInt()
    } ?: throw IllegalArgumentException("Version must be in format: X.Y.Z")
}
