package pro.aeternum.data.repository

import pro.aeternum.data.source.local.ThirdLocalSource
import pro.aeternum.domain.repository.ThirdRepository

internal class DefaultThirdRepository(
    private val thirdLocalSource: ThirdLocalSource,
) : ThirdRepository {

    override suspend fun getHailMary(): String = thirdLocalSource.getHailMary().text
}
