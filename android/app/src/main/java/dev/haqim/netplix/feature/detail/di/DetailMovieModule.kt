package dev.haqim.netplix.feature.detail.di

import dev.haqim.netplix.feature.detail.data.remote.DetailMovieRemoteDataSource
import dev.haqim.netplix.feature.detail.data.remote.config.DetailMovieService
import dev.haqim.netplix.feature.detail.data.repository.DetailMovieRepository
import dev.haqim.netplix.feature.detail.domain.repository.IDetailMovieRepository
import dev.haqim.netplix.feature.detail.domain.usecase.GetTrailerMovieUseCase
import dev.haqim.netplix.feature.detail.ui.DetailMovieViewModel
import dev.haqim.netplix.feature.discover.data.remote.DiscoverRemoteDataSource
import org.koin.androidx.viewmodel.dsl.viewModel
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
        DetailMovieRemoteDataSource(get())
    }
    single <IDetailMovieRepository> { DetailMovieRepository(get()) }
    factory { GetTrailerMovieUseCase() }
    viewModel { DetailMovieViewModel(get(), get()) }
}