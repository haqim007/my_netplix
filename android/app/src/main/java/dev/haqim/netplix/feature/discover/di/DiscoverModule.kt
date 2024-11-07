package dev.haqim.netplix.feature.discover.di

import dev.haqim.netplix.feature.discover.data.remote.DiscoverRemoteDataSource
import dev.haqim.netplix.feature.discover.data.remote.DiscoverService
import dev.haqim.netplix.feature.discover.data.repository.DiscoverRepository
import dev.haqim.netplix.feature.discover.domain.repository.IDiscoverRepository
import dev.haqim.netplix.feature.discover.domain.usecase.DiscoverMoviesUseCase
import dev.haqim.netplix.feature.discover.ui.DiscoverMoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
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
    single <IDiscoverRepository> { DiscoverRepository(get(), get()) }
    factory { DiscoverMoviesUseCase() }
    viewModel { DiscoverMoviesViewModel(get()) }
}