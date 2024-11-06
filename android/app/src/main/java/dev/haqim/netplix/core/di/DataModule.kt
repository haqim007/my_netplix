package dev.haqim.netplix.core.di

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module


// define qualifiers
private val devicePreference = named("DevicePreference")

// create DataStore objects using PreferencesDataStore extension property
val Context.deviceDataStore by preferencesDataStore(name = "device_preference")

// functions to provide DataStore instances
fun provideDeviceDataStore(context: Context): DataStore<Preferences> {
    return context.deviceDataStore
}

internal val ktorHttpClient = named("ktor_engine")


val dataModule = module {
    single(devicePreference) { provideDeviceDataStore(androidContext()) }
    single(ktorHttpClient) {
        HttpClient(CIO) {
            install(Logging) {
//            logger = Logger.ANDROID
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("ktorHttpClient", message)
                    }
                }
                level = LogLevel.ALL

                sanitizeHeader { header -> header == HttpHeaders.Authorization }
            }

            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                    encodeDefaults = true
                })
            }

            install(HttpTimeout){
                socketTimeoutMillis = 30000L
                connectTimeoutMillis = 30000L
                requestTimeoutMillis = 30000L
            }
        }
    }
}