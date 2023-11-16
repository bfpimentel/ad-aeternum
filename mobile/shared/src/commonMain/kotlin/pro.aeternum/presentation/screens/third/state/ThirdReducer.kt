package pro.aeternum.presentation.screens.third.state

import pro.aeternum.domain.model.Third
import pro.aeternum.presentation.state.Reducer

internal object ThirdReducer {

    operator fun invoke(): Reducer<ThirdState, ThirdActions> = { state, action ->
        when (action) {
            is ThirdActions.SetIsLoading -> state.copy(isLoading = true, hasError = false)
            is ThirdActions.SetThird -> action.third.let { third ->
                val groups = third.mapGroups()

                state.copy(
                    title = third.title,
                    subtitle = third.subtitle.ifEmpty { null },
                    groups = groups,
                    prayers = groups.flatMap(ThirdState.Group::prayers),
                    isLoading = false,
                )
            }
            is ThirdActions.Swipe -> if (action.index > state.currentPrayerIndex) {
                state.nextPrayer()
            } else {
                state.previousPrayer()
            }
            is ThirdActions.SetHasError -> state.copy(hasError = true)
            else -> state
        }
    }

    private fun Third.mapGroups(): List<ThirdState.Group> = groups.map { group ->
        ThirdState.Group(
            prayers = group.steps.flatMap { step ->
                val prayer = prayers.first { prayer -> prayer.type == step.type }.let { prayer ->
                    ThirdState.Prayer(
                        title = prayer.title,
                        subtitle = prayer.subtitle.ifEmpty { null },
                        paragraphs = prayer.paragraphs
                    )
                }
                MutableList(step.count) { prayer }
            }
        )
    }

    private fun ThirdState.nextPrayer(): ThirdState {
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

    private fun ThirdState.previousPrayer(): ThirdState {
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
