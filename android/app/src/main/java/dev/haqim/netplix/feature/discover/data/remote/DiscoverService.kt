package dev.haqim.netplix.feature.discover.data.remote

import dev.haqim.netplix.core.data.remote.KtorService
import dev.haqim.netplix.core.data.remote.response.MoviesResponse
import io.ktor.client.call.body
import io.ktor.client.request.get

class DiscoverService (
    override val BASE_URL: String,
    override val API_VERSION: String,
    override val API_KEY: String
) : KtorService(){
    val path = "movie"

    suspend fun fetchMovieByKeyword(keyword: String, page: Int = 1): MoviesResponse {
        val response = client.get {
            val param = mutableListOf(
                Pair("page", page.toString())
            )
            var fullpath = "discover/$path"
            if (keyword.isNotBlank()){
                fullpath = "search/$path"
                param.add(Pair("query", keyword))
            }
            endpoint(fullpath, param)
        }

        checkOrThrowError(response)

        return response.body<MoviesResponse>()
    }

}