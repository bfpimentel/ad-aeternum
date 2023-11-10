package pro.aeternum.presentation.screens.third.state

internal data class ThirdState(
    val title: String,
    val subtitle: String?,
    val prayer: Prayer,
    val currentGroupIndex: Int,
    val currentStepIndex: Int,
    val isLoading: Boolean,
    val groups: List<Group>, // control only
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
            prayer = Prayer(title = "", subtitle = null, paragraphs = listOf()),
            currentGroupIndex = 0,
            currentStepIndex = 0,
            isLoading = true,
            groups = listOf(),
        )
    }
}
