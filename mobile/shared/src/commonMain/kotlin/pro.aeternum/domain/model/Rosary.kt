package pro.aeternum.domain.model

import pro.aeternum.data.model.RosaryResponse

internal data class Rosary(
    val id: String,
    val title: String,
    val subtitle: String,
    val groups: List<Group>,
    val prayers: List<Prayer>,
)

internal data class Group(
    val steps: List<Step>,
)

internal data class Step(
    val type: String,
    val count: Int,
)

internal data class Prayer(
    val type: String,
    val title: String,
    val subtitle: String,
    val paragraphs: List<String>,
)

internal fun RosaryResponse.toDomainModel(): Rosary = Rosary(
    id = this.id,
    title = this.title,
    subtitle = this.subtitle,
    groups = this.groups.map { groupResponse ->
        Group(
            steps = groupResponse.steps.map { stepResponse ->
                Step(
                    type = stepResponse.type,
                    count = stepResponse.count,
                )
            }
        )
    },
    prayers = this.prayers.map { prayerResponse ->
        Prayer(
            type = prayerResponse.type,
            title = prayerResponse.title,
            subtitle = prayerResponse.subtitle,
            paragraphs = prayerResponse.paragraphs,
        )
    }
)
