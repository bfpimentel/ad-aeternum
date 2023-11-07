package pro.aeternum.platform

expect class LocaleGetter() {

    fun getLanguage(): String

    fun getCountry(): String?
}
