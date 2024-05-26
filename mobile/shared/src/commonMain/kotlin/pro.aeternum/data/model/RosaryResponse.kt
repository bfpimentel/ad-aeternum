package pro.aeternum.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RosaryResponse(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("subtitle") val subtitle: String,
    @SerialName("groups") val groups: List<GroupResponse>,
    @SerialName("prayers") val prayers: List<PrayerResponse>,
)

@Serializable
internal data class GroupResponse(
    @SerialName("steps") val steps: List<StepResponse>,
)

@Serializable
internal data class StepResponse(
    @SerialName("type") val type: String,
    @SerialName("count") val count: Int,
)

@Serializable
internal data class PrayerResponse(
    @SerialName("type") val type: String,
    @SerialName("title") val title: String,
    @SerialName("subtitle") val subtitle: String,
    @SerialName("paragraphs") val paragraphs: List<String>,
)
