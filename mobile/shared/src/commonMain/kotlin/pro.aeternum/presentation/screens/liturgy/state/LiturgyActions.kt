package pro.aeternum.presentation.screens.liturgy.state

internal sealed interface LiturgyActions {

    data object Load : LiturgyActions

    data class SetLiturgy(val text: String) : LiturgyActions
}
