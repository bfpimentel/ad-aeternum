package pro.aeternum.data.repository

import pro.aeternum.data.api.LiturgyAPI
import pro.aeternum.domain.model.Liturgy
import pro.aeternum.domain.repository.LiturgyRepository

internal class DefaultLiturgyRepository(
    private val liturgyAPI: LiturgyAPI,
) : LiturgyRepository {

    override suspend fun getLiturgy(): Liturgy = liturgyAPI.getLiturgy().let { response ->
        Liturgy(text = response.text)
    }
}
