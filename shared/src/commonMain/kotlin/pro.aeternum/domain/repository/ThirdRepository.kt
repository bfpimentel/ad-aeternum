package pro.aeternum.domain.repository

internal interface ThirdRepository {

    suspend fun getHailMary(): String
}
