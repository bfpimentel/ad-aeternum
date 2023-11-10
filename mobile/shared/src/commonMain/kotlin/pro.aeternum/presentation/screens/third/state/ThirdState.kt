package pro.aeternum.presentation.screens.third.state

internal data class ThirdState(
    val isLoading: Boolean,
    val text: String,
) {

    companion object {
        val INITIAL = ThirdState(
            isLoading = true,
            text = "",
        )
    }
}
