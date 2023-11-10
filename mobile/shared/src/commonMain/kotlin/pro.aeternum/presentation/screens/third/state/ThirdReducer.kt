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
                    currentPrayer = groups.first().prayers.first(),
                )
            }
            is ThirdActions.Next -> {
                val groupIndex: Int? = state.groups.getOrNull(state.currentGroup)?.let { group ->
                    val prayer = group.prayers.getOrNull(state.currentStep + 1)
                    state.currentGroup.takeIf { prayer != null }
                } ?: state.groups.getOrNull(state.currentGroup + 1)?.run {
                    state.currentGroup + 1
                }

                val stepIndex: Int? = if (groupIndex == null) {
                    null
                } else {
                    state.groups[groupIndex].let { group ->
                        val stepIndex = state.currentStep + 1
                        stepIndex.takeIf { group.prayers.getOrNull(stepIndex) != null }
                    }
                }

                if (groupIndex != null && stepIndex != null) {
                    state.copy(
                        currentPrayer = state.groups[groupIndex].prayers[stepIndex],
                        currentGroup = groupIndex,
                        currentStep = stepIndex,
                    )
                } else {
                    state
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
                        subtitle = prayer.subtitle,
                        paragraphs = prayer.paragraphs
                    )
                }
                MutableList(step.count) { prayer }
            }
        )
    }
}
