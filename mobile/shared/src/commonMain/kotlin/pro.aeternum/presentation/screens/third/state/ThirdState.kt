package pro.aeternum.presentation.screens.third.state

internal data class ThirdState(
    val title: String,
    val subtitle: String?,
    val groups: List<Group>,
    val currentPrayer: Prayer,
    val currentGroup: Int,
    val currentStep: Int,
    val isLoading: Boolean,
) {

    data class Group(
        val prayers: List<Prayer>,
    )

    data class Prayer(
        val title: String,
        val subtitle: String?,
        val paragraphs: List<String>,
    )

    companion object {
        val INITIAL = ThirdState(
            title = "",
            subtitle = null,
            groups = listOf(),
            currentPrayer = Prayer(title = "", subtitle = null, paragraphs = listOf()),
            currentGroup = 0,
            currentStep = 0,
            isLoading = true,
        )
    }
}
