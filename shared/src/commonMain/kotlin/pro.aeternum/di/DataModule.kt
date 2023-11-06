package pro.aeternum.di

import io.ktor.client.HttpClient
import pro.aeternum.data.API

internal interface DataModule {
    val api: API
}

internal class DefaultDataModule : DataModule {

    override val api: API
        get() = API(client = HttpClient())
}
