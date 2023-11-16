package pro.aeternum.presentation.screens.liturgy.state

internal data class LiturgyState(
    val isLoading: Boolean,
    val text: String,
    val hasError: Boolean,
) {

    companion object {
        val INITIAL = LiturgyState(
            isLoading = true,
            text = "",
            hasError = false,
        )
    }
}
