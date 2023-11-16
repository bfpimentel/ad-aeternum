package pro.aeternum.presentation.screens.third.state

internal data class ThirdState(
    val title: String,
    val subtitle: String?,
    val prayers: List<Prayer>,
    val currentPrayerIndex: Int,
    val currentGroupIndex: Int,
    val currentStepIndex: Int,
    val isLoading: Boolean,
    val isNextEnabled: Boolean,
    val isPreviousEnabled: Boolean,
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
        val INITIAL = ThirdState(
            title = "",
            subtitle = null,
            prayers = listOf(),
            currentGroupIndex = 0,
            currentStepIndex = 0,
            isLoading = true,
            isNextEnabled = true,
            isPreviousEnabled = false,
            currentPrayerIndex = 0,
            groups = listOf(),
        )
    }
}
