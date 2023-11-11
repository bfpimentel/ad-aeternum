package pro.aeternum.presentation.screens.thirdslist.state

internal data class ThirdsListState(
    val thirds: List<Third>,
    val isLoading: Boolean,
) {

    data class Third(
        val id: String,
        val title: String,
        val subtitle: String?,
    )

    companion object {
        val INITIAL = ThirdsListState(
            thirds = listOf(),
            isLoading = true,
        )
    }
}
