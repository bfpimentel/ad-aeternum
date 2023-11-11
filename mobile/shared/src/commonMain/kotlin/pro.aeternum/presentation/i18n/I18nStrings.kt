package pro.aeternum.presentation.i18n

internal interface I18nStrings {

    val main: Main
    val thirdsList: ThirdsList
    val third: Third
    val liturgy: Liturgy

    interface Main {
        val title: String
    }

    interface ThirdsList {
        val title: String
    }

    interface Third {
        val title: String
    }

    interface Liturgy {
        val title: String
    }
}

internal class BrazilianPortugueseStrings : I18nStrings {

    override val main = object : I18nStrings.Main {
        override val title: String = "Ad Aeternum"
    }

    override val thirdsList = object : I18nStrings.ThirdsList {
        override val title: String = "Terços"
    }

    override val liturgy = object : I18nStrings.Liturgy {
        override val title: String = "Liturgia"
    }

    override val third = object : I18nStrings.Third {
        override val title: String = "Terço"
    }
}
