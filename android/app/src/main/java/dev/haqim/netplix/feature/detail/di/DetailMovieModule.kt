package dev.haqim.netplix.feature.detail.di

import dev.haqim.netplix.feature.detail.data.remote.DetailMovieService
import dev.haqim.netplix.feature.discover.data.remote.DiscoverRemoteDataSource
import org.koin.dsl.module

val detailMovieModule = module {
    single {
        DetailMovieService(
            getProperty("BASE_URL"),
            getProperty("API_VERSION"),
            getProperty("API_KEY")
        )
    }
    single {
        DiscoverRemoteDataSource(get())
    }
}