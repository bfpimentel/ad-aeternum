package pro.aeternum.presentation.i18n

internal interface I18nStrings {

    val main: Main
    val liturgy: Liturgy
    val third: Third

    interface Main {
        val title: String
    }

    interface Liturgy {
        val title: String
    }

    interface Third {
        val title: String
    }
}

internal class EnglishStrings : I18nStrings {

    override val main = object : I18nStrings.Main {
        override val title: String = "Ad Aeternum"
    }

    override val liturgy = object : I18nStrings.Liturgy {
        override val title: String = "Liturgy"
    }

    override val third = object : I18nStrings.Third {
        override val title: String = "Third"
    }
}

internal class BrazilianPortugueseStrings : I18nStrings {

    override val main = object : I18nStrings.Main {
        override val title: String = "Ad Aeternum"
    }

    override val liturgy = object : I18nStrings.Liturgy {
        override val title: String = "Liturgia"
    }

    override val third = object : I18nStrings.Third {
        override val title: String = "Terço"
    }
}
