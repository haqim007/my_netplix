package dev.haqim.netplix.feature.home.data

import dev.haqim.netplix.core.data.mechanism.getResult
import dev.haqim.netplix.feature.home.data.remote.config.HomeService

class HomeRemoteDataSource(
    private val service: HomeService
) {
    suspend fun fetchPopularMovies() = getResult {
        service.fetchPopularMovies()
    }

    suspend fun fetchNowPlayingMovies() = getResult {
        service.fetchNowPlayingMovies()
    }

    suspend fun fetchMovieGenres() = getResult {
        service.fetchMovieGenres()
    }

    suspend fun fetchMovieByGenre(genreId: String) = getResult {
        service.fetchMoviesByGenre(genreId)
    }
}