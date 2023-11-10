package pro.aeternum.domain.repository

import pro.aeternum.domain.model.Liturgy

internal interface LiturgyRepository {

    suspend fun getLiturgy(): Liturgy
}
