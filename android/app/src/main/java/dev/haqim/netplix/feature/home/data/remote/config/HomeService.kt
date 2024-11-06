package dev.haqim.netplix.feature.home.data.remote.config

import dev.haqim.netplix.core.data.remote.KtorService
import dev.haqim.netplix.feature.home.data.remote.response.GenresResponse
import dev.haqim.netplix.core.data.remote.response.MoviesResponse
import io.ktor.client.call.body
import io.ktor.client.request.get

class HomeService (
    override val BASE_URL: String,
    override val API_VERSION: String,
    override val API_KEY: String
) : KtorService(){
    val path = "movie"
    suspend fun fetchPopularMovies(): MoviesResponse {
        val response = client.get {
            endpoint("$path/popular")
        }

        checkOrThrowError(response)

        return response.body<MoviesResponse>()
    }

    suspend fun fetchNowPlayingMovies(): MoviesResponse {
        val response = client.get {
            endpoint("$path/now_playing")
        }

        checkOrThrowError(response)

        return response.body<MoviesResponse>()
    }

    suspend fun fetchMovieGenres(): GenresResponse {
        val response = client.get {
            endpoint("genre/$path/list")
        }

        checkOrThrowError(response)

        return response.body<GenresResponse>()
    }

    suspend fun fetchMoviesByGenre(genreId: String): MoviesResponse {
        val response = client.get {
            endpoint(
                "discover/$path",
                listOf(
                    Pair("with_genres", genreId)
                )
            )
        }

        checkOrThrowError(response)

        return response.body<MoviesResponse>()
    }
}