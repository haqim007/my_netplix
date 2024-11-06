package dev.haqim.netplix.feature.home.di

import dev.haqim.netplix.feature.home.data.HomeRemoteDataSource
import dev.haqim.netplix.feature.home.data.remote.config.HomeService
import dev.haqim.netplix.feature.home.data.repository.HomeRepository
import dev.haqim.netplix.feature.home.domain.repository.IHomeRepository
import dev.haqim.netplix.feature.home.domain.usecase.GetLatestMoviesUseCase
import dev.haqim.netplix.feature.home.domain.usecase.GetMovieGenresUseCase
import dev.haqim.netplix.feature.home.domain.usecase.GetMoviesByGenreUseCase
import dev.haqim.netplix.feature.home.domain.usecase.GetPopularMoviesUseCase
import dev.haqim.netplix.feature.home.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    single {
        HomeService(
            getProperty("BASE_URL"),
            getProperty("API_VERSION"),
            getProperty("API_KEY")
        )
    }
    single {
        HomeRemoteDataSource(get())
    }
    single<IHomeRepository> {
        HomeRepository(get())
    }
    factory {
        GetPopularMoviesUseCase()
    }
    factory {
        GetLatestMoviesUseCase()
    }
    factory {
        GetMovieGenresUseCase()
    }
    factory {
        GetMoviesByGenreUseCase()
    }
    viewModel {
        HomeViewModel(get(), get(), get(), get())
    }
}