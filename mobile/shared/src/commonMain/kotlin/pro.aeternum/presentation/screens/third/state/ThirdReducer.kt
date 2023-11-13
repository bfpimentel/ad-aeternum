package pro.aeternum.presentation.screens.third.state

import pro.aeternum.domain.model.Third
import pro.aeternum.presentation.state.Reducer

internal object ThirdReducer {

    operator fun invoke(): Reducer<ThirdState, ThirdActions> = { state, action ->
        when (action) {
            is ThirdActions.SetThird -> action.third.let { third ->
                val groups = third.mapGroups()

                state.copy(
                    title = third.title,
                    subtitle = third.subtitle.ifEmpty { null },
                    groups = groups,
                    prayer = groups.first().prayers.first(),
                    isLoading = false,
                )
            }
            is ThirdActions.Next -> {
                val groupIndex: Int? = state.groups.getOrNull(state.currentGroupIndex)?.let { group ->
                    val prayer = group.prayers.getOrNull(state.currentStepIndex + 1)
                    state.currentGroupIndex.takeIf { prayer != null }
                } ?: state.groups.getOrNull(state.currentGroupIndex + 1)?.run {
                    state.currentGroupIndex + 1
                }

                val stepIndex: Int? = when {
                    groupIndex == null -> null
                    groupIndex != state.currentGroupIndex -> 0
                    else -> state.currentStepIndex + 1
                }

                if (groupIndex != null && stepIndex != null) {
                    val lastGroupIndex = state.groups.lastIndex
                    val lastPrayerIndex = state.groups.last().prayers.lastIndex

                    state.copy(
                        prayer = state.groups[groupIndex].prayers[stepIndex],
                        currentGroupIndex = groupIndex,
                        currentStepIndex = stepIndex,
                        isNextEnabled = if (groupIndex == lastGroupIndex) stepIndex != lastPrayerIndex else true,
                        isPreviousEnabled = true,
                    )
                } else {
                    state.copy(isNextEnabled = false)
                }
            }
            is ThirdActions.Previous -> {
                val groupIndex: Int? = state.groups.getOrNull(state.currentGroupIndex)?.let { group ->
                    val prayer = group.prayers.getOrNull(state.currentStepIndex - 1)
                    state.currentGroupIndex.takeIf { prayer != null }
                } ?: state.groups.getOrNull(state.currentGroupIndex - 1)?.run {
                    state.currentGroupIndex - 1
                }

                val stepIndex: Int? = when {
                    groupIndex == null -> null
                    groupIndex != state.currentGroupIndex -> state.groups[groupIndex].prayers.lastIndex
                    else -> state.currentStepIndex - 1
                }

                if (groupIndex != null && stepIndex != null) {
                    state.copy(
                        prayer = state.groups[groupIndex].prayers[stepIndex],
                        currentGroupIndex = groupIndex,
                        currentStepIndex = stepIndex,
                        isPreviousEnabled = groupIndex != 0 || stepIndex != 0,
                        isNextEnabled = true,
                    )
                } else {
                    state.copy(isPreviousEnabled = false)
                }
            }
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
}
