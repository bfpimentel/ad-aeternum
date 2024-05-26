package pro.aeternum.presentation.screens.rosarylist.state

internal data class RosariesListState(
    val rosaries: List<Rosary>,
    val isLoading: Boolean,
    val hasError: Boolean,
) {

    data class Rosary(
        val id: String,
        val title: String,
        val subtitle: String?,
    )

    companion object {
        val INITIAL = RosariesListState(
            rosaries = listOf(),
            isLoading = true,
            hasError = false,
        )
    }
}
