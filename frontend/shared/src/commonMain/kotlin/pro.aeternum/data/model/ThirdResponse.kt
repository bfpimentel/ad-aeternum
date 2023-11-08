package pro.aeternum.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ThirdResponse(
    val title: String,
//    val prayers: List<Prayer>,
) {

    @Serializable
    data class Prayer(
        @SerialName("title") val title: String,
    )
}
