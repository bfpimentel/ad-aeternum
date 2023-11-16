package pro.aeternum.presentation.screens.liturgy.state

internal sealed interface LiturgyActions {

    data object Load : LiturgyActions

    data object SetIsLoading : LiturgyActions

    data class SetLiturgy(val text: String) : LiturgyActions

    data object SetHasError : LiturgyActions
}
