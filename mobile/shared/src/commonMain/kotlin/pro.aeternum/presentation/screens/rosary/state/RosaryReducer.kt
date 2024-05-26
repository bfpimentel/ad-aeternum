package pro.aeternum.presentation.screens.rosary.state

import pro.aeternum.domain.model.Rosary
import pro.aeternum.presentation.state.Reducer

internal object RosaryReducer {

    operator fun invoke(): Reducer<RosaryState, RosaryActions> = { state, action ->
        when (action) {
            is RosaryActions.SetIsLoading -> state.copy(isLoading = true, hasError = false)
            is RosaryActions.SetRosary -> action.rosary.let { rosary ->
                val groups = rosary.mapGroups()

                state.copy(
                    title = rosary.title,
                    subtitle = rosary.subtitle.ifEmpty { null },
                    groups = groups,
                    prayers = groups.flatMap(RosaryState.Group::prayers),
                    isLoading = false,
                )
            }
            is RosaryActions.Swipe -> if (action.index > state.currentPrayerIndex) {
                state.nextPrayer()
            } else {
                state.previousPrayer()
            }
            is RosaryActions.SetHasError -> state.copy(hasError = true)
            else -> state
        }
    }

    private fun Rosary.mapGroups(): List<RosaryState.Group> = groups.map { group ->
        RosaryState.Group(
            prayers = group.steps.flatMap { step ->
                val prayer = prayers.first { prayer -> prayer.type == step.type }.let { prayer ->
                    RosaryState.Prayer(
                        title = prayer.title,
                        subtitle = prayer.subtitle.ifEmpty { null },
                        paragraphs = prayer.paragraphs
                    )
                }
                MutableList(step.count) { prayer }
            }
        )
    }

    private fun RosaryState.nextPrayer(): RosaryState {
        val groupIndex: Int? = groups.getOrNull(currentGroupIndex)?.let { group ->
            val prayer = group.prayers.getOrNull(currentStepIndex + 1)
            currentGroupIndex.takeIf { prayer != null }
        } ?: groups.getOrNull(currentGroupIndex + 1)?.run { currentGroupIndex + 1 }

        val stepIndex: Int? = when {
            groupIndex == null -> null
            groupIndex != currentGroupIndex -> 0
            else -> currentStepIndex + 1
        }

        return if (groupIndex != null && stepIndex != null) {
            val lastGroupIndex = groups.lastIndex
            val lastPrayerIndex = groups.last().prayers.lastIndex

            copy(
                currentPrayerIndex = currentPrayerIndex + 1,
                currentGroupIndex = groupIndex,
                currentStepIndex = stepIndex,
                isNextEnabled = if (groupIndex == lastGroupIndex) stepIndex != lastPrayerIndex else true,
                isPreviousEnabled = true,
            )
        } else {
            copy(isNextEnabled = false)
        }
    }

    private fun RosaryState.previousPrayer(): RosaryState {
        val groupIndex: Int? = groups.getOrNull(currentGroupIndex)?.let { group ->
            val prayer = group.prayers.getOrNull(currentStepIndex - 1)
            currentGroupIndex.takeIf { prayer != null }
        } ?: groups.getOrNull(currentGroupIndex - 1)?.run { currentGroupIndex - 1 }

        val stepIndex: Int? = when {
            groupIndex == null -> null
            groupIndex != currentGroupIndex -> groups[groupIndex].prayers.lastIndex
            else -> currentStepIndex - 1
        }

        return if (groupIndex != null && stepIndex != null) {
            copy(
                currentPrayerIndex = currentPrayerIndex - 1,
                currentGroupIndex = groupIndex,
                currentStepIndex = stepIndex,
                isPreviousEnabled = groupIndex != 0 || stepIndex != 0,
                isNextEnabled = true,
            )
        } else {
            copy(isPreviousEnabled = false)
        }
    }
}
