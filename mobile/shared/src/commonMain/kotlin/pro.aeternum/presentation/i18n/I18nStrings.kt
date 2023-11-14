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
        val inProgress: String
    }

    interface About {
        val title: String
        val goalsTitle: String
        val goalsParagraphs: List<String>
        val storyTitle: String
        val storyParagraphs: List<String>
        val developmentTitle: String
        val developmentParagraphs: List<String>
        val acknowledgementTitle: String
        val acknowledgementParagraphs: List<String>
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
        override val inProgress: String = "A tela de Liturgia ainda está em progresso."
    }

    override val third = object : I18nStrings.Third {
        override val title: String = "Terço"
    }

    override val about = object : I18nStrings.About {
        override val title: String = "Sobre"
        override val goalsTitle: String = "Objetivos"
        override val goalsParagraphs: List<String> = listOf(
            "1. AD AETERNUM é um projeto pessoal onde seu objetivo principal é ter uma base de dados sólida, " +
                    "estável e aberta sobre a Santa Igreja para usuários e desenvolvedores.",
            "2. A aplicação nunca terá qualquer tipo de funcionalidade paga e nunca será cobrado o use de seu banco " +
                    "de dados.",
        )
        override val storyTitle: String = "História"
        override val storyParagraphs: List<String> = listOf(
            "TBD"
        )
        override val developmentTitle: String = "Desenvolvimento"
        override val developmentParagraphs: List<String> = listOf(
            "O objetivo secundário é para preencher uma lacuna em meu portfólio com tecnologias novas e " +
                    "interessantes para desenvolvimento mobile, backend e web.",
        )
        override val acknowledgementTitle: String = "Agradecimentos"
        override val acknowledgementParagraphs: List<String> = listOf(
            "Agradeço a Deus pela oportunidade e habilidades que Ele me deu.",
            "Atenciosamente,\nBruno Pimentel"
        )
    }
}
