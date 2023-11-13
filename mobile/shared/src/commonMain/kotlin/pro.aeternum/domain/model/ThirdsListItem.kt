package pro.aeternum.domain.model

import pro.aeternum.data.model.ThirdsListItemResponse

internal data class ThirdsListItem(
    val id: String,
    val title: String,
    val subtitle: String?,
)

internal fun ThirdsListItemResponse.toDomainModel(): ThirdsListItem = ThirdsListItem(
    id = id,
    title = title,
    subtitle = subtitle.takeIf(String::isNotEmpty),
)
