package dev.haqim.netplix.feature.discover.di

import dev.haqim.netplix.feature.discover.data.remote.DiscoverRemoteDataSource
import dev.haqim.netplix.feature.discover.data.remote.DiscoverService
import org.koin.dsl.module

val discoverModule = module {
    single {
        DiscoverService(
            getProperty("BASE_URL"),
            getProperty("API_VERSION"),
            getProperty("API_KEY")
        )
    }
    single {
        DiscoverRemoteDataSource(get())
    }
}