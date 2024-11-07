package dev.haqim.netplix.feature.detail.data.remote

import dev.haqim.netplix.core.data.mechanism.getResult
import dev.haqim.netplix.feature.detail.data.remote.config.DetailMovieService

class DetailMovieRemoteDataSource(
    private val service: DetailMovieService
) {
    suspend fun fetchMovieTrailer(movieId: Int) = getResult {
        service.fetchMovieTrailer(movieId)
    }
}