package dev.haqim.netplix.feature.detail.data.remote.config

import dev.haqim.netplix.core.data.remote.KtorService
import dev.haqim.netplix.feature.detail.data.remote.response.TrailerResponse
import io.ktor.client.call.body
import io.ktor.client.request.get

class DetailMovieService (
    override val BASE_URL: String,
    override val API_VERSION: String,
    override val API_KEY: String
) : KtorService(){
    val path = "movie"

    suspend fun fetchMovieTrailer(movieId: Int): TrailerResponse {
        val response = client.get {
            endpoint(
                "$path/$movieId/videos"
            )
        }

        checkOrThrowError(response)

        return response.body<TrailerResponse>()
    }

}