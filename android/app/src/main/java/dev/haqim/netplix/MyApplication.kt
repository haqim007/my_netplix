package dev.haqim.netplix

import android.app.Application
import dev.haqim.netplix.core.di.dataModule
import dev.haqim.netplix.core.di.dispatcherModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            properties(mapOf("BASE_URL" to BuildConfig.BASE_URL))
            properties(mapOf("API_VERSION" to BuildConfig.API_VERSION))
            properties(mapOf("BASE_IMAGE_URL" to BuildConfig.BASE_IMAGE_URL))
            properties(mapOf("API_KEY" to BuildConfig.API_KEY))
            // Load modules
            modules(
                dispatcherModule, dataModule
            )
        }

    }
}