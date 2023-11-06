package pro.aeternum.data.source.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource
import pro.aeternum.data.model.ThirdResponse
import pro.aeternum.platform.FileReader
import pro.aeternum.platform.LocaleGetter

internal class ThirdLocalSource(
    private val localeGetter: LocaleGetter,
    private val fileReader: FileReader,
    private val ioDispatcher: CoroutineDispatcher,
) {

    @OptIn(ExperimentalResourceApi::class)
    suspend fun getHailMary(): ThirdResponse.Prayer {
        val language = localeGetter.getLanguage()
        return withContext(ioDispatcher) {
            println("Test")

            val file: String = resource("hail_mary_${language}").readBytes().decodeToString()

            println(file)

            Json.decodeFromString(deserializer = ThirdResponse.Prayer.serializer(), string = file)
        }
    }
}
