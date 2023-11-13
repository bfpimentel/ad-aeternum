package pro.aeternum.presentation.i18n

internal interface I18nStrings {

    val main: Main
    val thirdsList: ThirdsList
    val third: Third
    val liturgy: Liturgy
    val about: About

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

    interface About {
        val title: String
        val paragraphs: List<String>
    }
}

internal class BrazilianPortugueseStrings : I18nStrings {

    override val main = object : I18nStrings.Main {
        override val title: String = "AD AETERNUM"
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

    override val about = object : I18nStrings.About {
        override val title: String = "Sobre"
        override val paragraphs: List<String> = listOf(
            "AD AETERNUM é um projeto pessoal onde seu objetivo principal é ter uma base de dados sólida, " +
                    "estável e aberta sobre a Santissima Igreja Católica, para que qualquer pessoa possa usar " +
                    "sem qualquer gasto.",
            "O objetivo secundário é para preencher uma lacuna em meu portfólio com tecnologias novas e " +
                    "interessantes para desenvolvimento mobile, backend e web.",
            "A aplicação NUNCA terá qualquer tipo de funcionalidade paga.",
            "Agradeço a Deus pela oportunidade e habilidades que Ele me deu.",
            "Atenciosamente,\nBruno Pimentel"
        )
    }
}
