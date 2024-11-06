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

    suspend fun fetchMovieByKeyword(keyword: String, page: Int = 1, genreId: String? = null): MoviesResponse {
        val response = client.get {
            val param = mutableListOf(
                Pair("with_keywords", keyword),
                Pair("page", page.toString())
            )
            if (genreId != null){
                param.add(Pair("with_genres", genreId))
            }
            endpoint(
                "discover/$path",
                param
            )
        }

        checkOrThrowError(response)

        return response.body<MoviesResponse>()
    }

}