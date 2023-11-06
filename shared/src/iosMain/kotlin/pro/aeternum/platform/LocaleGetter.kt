package pro.aeternum.platform

import platform.Foundation.NSLocale
import platform.Foundation.countryCode
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

actual class LocaleGetter actual constructor() {

    actual fun getLanguage(): String = NSLocale.currentLocale.languageCode

    actual fun getCountry(): String? = NSLocale.currentLocale.countryCode
}
