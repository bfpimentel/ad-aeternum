package pro.aeternum.platform

import java.util.Locale

actual class LocaleGetter actual constructor() {

    actual fun getLanguage(): String = Locale.getDefault().language

    actual fun getCountry(): String? = Locale.getDefault().country
}
