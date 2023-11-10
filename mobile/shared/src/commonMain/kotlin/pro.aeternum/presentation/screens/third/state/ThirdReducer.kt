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

                val stepIndex: Int? = if (groupIndex == null) {
                    null
                } else {
                    state.groups[groupIndex].let { group ->
                        val stepIndex = state.currentStepIndex + 1
                        stepIndex.takeIf { group.prayers.getOrNull(stepIndex) != null }
                    }
                }

                if (groupIndex != null && stepIndex != null) {
                    state.copy(
                        prayer = state.groups[groupIndex].prayers[stepIndex],
                        currentGroupIndex = groupIndex,
                        currentStepIndex = stepIndex,
                    )
                } else {
                    // todo: set finished state
                    state
                }
            }
            is ThirdActions.Previous -> {
                // todo
                state
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
