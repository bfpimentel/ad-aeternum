package pro.aeternum.presentation.screens.rosary.state

internal data class RosaryState(
    val title: String,
    val subtitle: String?,
    val prayers: List<Prayer>,
    val currentPrayerIndex: Int,
    val currentGroupIndex: Int,
    val currentStepIndex: Int,
    val isLoading: Boolean,
    val isNextEnabled: Boolean,
    val isPreviousEnabled: Boolean,
    val hasError: Boolean,
    // control only
    val groups: List<Group>,
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
        val INITIAL = RosaryState(
            title = "",
            subtitle = null,
            prayers = listOf(),
            currentPrayerIndex = 0,
            currentGroupIndex = 0,
            currentStepIndex = 0,
            isLoading = true,
            isNextEnabled = true,
            isPreviousEnabled = false,
            hasError = false,
            groups = listOf(),
        )
    }
}
