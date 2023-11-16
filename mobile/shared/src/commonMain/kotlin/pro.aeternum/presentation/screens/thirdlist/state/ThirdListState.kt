package pro.aeternum.presentation.screens.thirdlist.state

internal data class ThirdListState(
    val thirds: List<Third>,
    val isLoading: Boolean,
    val hasError: Boolean,
) {

    data class Third(
        val id: String,
        val title: String,
        val subtitle: String?,
    )

    companion object {
        val INITIAL = ThirdListState(
            thirds = listOf(),
            isLoading = true,
            hasError = false,
        )
    }
}
