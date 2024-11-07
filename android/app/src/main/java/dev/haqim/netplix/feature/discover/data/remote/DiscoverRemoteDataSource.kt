package dev.haqim.netplix.feature.discover.data.remote

import dev.haqim.netplix.core.data.mechanism.getResult
import dev.haqim.netplix.core.data.remote.response.MoviesResponse
import io.ktor.client.call.body
import io.ktor.client.request.get

class DiscoverRemoteDataSource(
    private val service: DiscoverService
) {
    suspend fun fetchMovieByKeyword(
        keyword: String,
        page: Int = 1
    ) = getResult {
        service.fetchMovieByKeyword(keyword, page)
    }
}