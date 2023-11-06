package pro.aeternum.data.repository

import pro.aeternum.data.source.remote.LiturgyRemoteSource
import pro.aeternum.domain.model.Liturgy
import pro.aeternum.domain.repository.LiturgyRepository

internal class DefaultLiturgyRepository(
    private val liturgyRemoteSource: LiturgyRemoteSource,
) : LiturgyRepository {

    override suspend fun getLiturgy(): Liturgy = liturgyRemoteSource.getLiturgy().let { response ->
        Liturgy(text = response.text)
    }
}
