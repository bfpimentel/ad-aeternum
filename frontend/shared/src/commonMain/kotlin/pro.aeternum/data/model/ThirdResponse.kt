package pro.aeternum.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

internal data class ThirdResponse(
    val name: String,
    val groups: List<Group>,
) {

    data class Group(
        val prayers: List<Prayer>,
    )

    @Serializable
    data class Prayer(
        @SerialName("title") val title: String,
        @SerialName("text") val text: String,
    )
}
