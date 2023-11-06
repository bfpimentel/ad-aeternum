package pro.aeternum.presentation.navigation

internal sealed interface AdAeternumDestinations {

    val id: String

    data object Liturgy : AdAeternumDestinations {
        override val id: String = "liturgy"
    }

    data object Placeholder : AdAeternumDestinations {
        override val id: String = "placeholder"
    }
}
