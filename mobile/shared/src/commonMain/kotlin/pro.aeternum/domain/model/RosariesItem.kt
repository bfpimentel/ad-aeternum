package pro.aeternum.domain.model

import pro.aeternum.data.model.RosariesItemResponse

internal data class RosariesItem(
    val id: String,
    val title: String,
    val subtitle: String?,
)

internal fun RosariesItemResponse.toDomainModel(): RosariesItem = RosariesItem(
    id = id,
    title = title,
    subtitle = subtitle.takeIf(String::isNotEmpty),
)
