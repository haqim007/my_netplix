package dev.haqim.netplix.feature.detail.data.repository

import dev.haqim.netplix.core.data.mechanism.NetworkBoundResource
import dev.haqim.netplix.core.data.mechanism.Resource
import dev.haqim.netplix.core.data.remote.response.MoviesResponse
import dev.haqim.netplix.core.data.toMovie
import dev.haqim.netplix.core.domain.model.Movie
import dev.haqim.netplix.core.domain.model.MovieTrailer
import dev.haqim.netplix.feature.detail.data.remote.DetailMovieRemoteDataSource
import dev.haqim.netplix.feature.detail.data.remote.response.TrailerResponse
import dev.haqim.netplix.feature.detail.domain.repository.IDetailMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class DetailMovieRepository(
    private val remoteDataSource: DetailMovieRemoteDataSource
): IDetailMovieRepository {
    override fun getTrailer(movie: Movie): Flow<Resource<Movie>> {
        return object: NetworkBoundResource<Movie, TrailerResponse>(){
            override suspend fun requestFromRemote(): Result<TrailerResponse> {
                return remoteDataSource.fetchMovieTrailer(movie.id)
            }

            override fun loadResult(responseData: TrailerResponse): Flow<Movie> {
                val trailer = if (responseData.results.isNotEmpty()){
                    responseData.results.first()
                } else null
                return flowOf(
                   movie.copy(
                       trailer = if (trailer != null) MovieTrailer(trailer.site, trailer.key)
                       else null
                   )
                )
            }

            override suspend fun onSuccess(responseData: TrailerResponse) {
                super.onSuccess(responseData)
                // TODO: Store to local
            }

        }.asFlow()
    }

}