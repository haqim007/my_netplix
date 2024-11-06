package dev.haqim.netplix.feature.home.data.repository

import dev.haqim.netplix.core.data.mechanism.NetworkBoundResource
import dev.haqim.netplix.core.data.mechanism.Resource
import dev.haqim.netplix.core.data.remote.response.MoviesResponse
import dev.haqim.netplix.core.data.toMovie
import dev.haqim.netplix.core.data.toMovies
import dev.haqim.netplix.core.domain.model.Genre
import dev.haqim.netplix.core.domain.model.Movie
import dev.haqim.netplix.feature.home.data.HomeRemoteDataSource
import dev.haqim.netplix.feature.home.data.remote.response.GenresResponse
import dev.haqim.netplix.feature.home.data.toGenres
import dev.haqim.netplix.feature.home.domain.repository.IHomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class HomeRepository(
    private val remoteDataSource: HomeRemoteDataSource
): IHomeRepository {
    override fun fetchPopularMovies(): Flow<Resource<List<Movie>>> {
        return object: NetworkBoundResource<List<Movie>, MoviesResponse>(){
            override suspend fun requestFromRemote(): Result<MoviesResponse> {
                return remoteDataSource.fetchPopularMovies()
            }

            override fun loadResult(responseData: MoviesResponse): Flow<List<Movie>> {
                return flowOf(
                    responseData.results.subList(0, 6).map { it.toMovie() }
                )
            }

            override suspend fun onSuccess(responseData: MoviesResponse) {
                super.onSuccess(responseData)
                // TODO: Store to local
            }

        }.asFlow()
    }

    override fun fetchNowPlayingMovies(): Flow<Resource<List<Movie>>> {
        return object: NetworkBoundResource<List<Movie>, MoviesResponse>(){
            override suspend fun requestFromRemote(): Result<MoviesResponse> {
                return remoteDataSource.fetchNowPlayingMovies()
            }

            override fun loadResult(responseData: MoviesResponse): Flow<List<Movie>> {
                return flowOf(
                    responseData.toMovies()
                )
            }

            override suspend fun onSuccess(responseData: MoviesResponse) {
                super.onSuccess(responseData)
                // TODO: Store to local
            }

        }.asFlow()
    }

    override fun fetchMovieGenres(): Flow<Resource<List<Genre>>> {
        return object: NetworkBoundResource<List<Genre>, GenresResponse>(){
            override suspend fun requestFromRemote(): Result<GenresResponse> {
                return remoteDataSource.fetchMovieGenres()
            }

            override fun loadResult(responseData: GenresResponse): Flow<List<Genre>> {
                return flowOf(
                    responseData.toGenres()
                )
            }

            override suspend fun onSuccess(responseData: GenresResponse) {
                super.onSuccess(responseData)
                // TODO: Store to local
            }

        }.asFlow()
    }

    override fun fetchMoviesByGenre(genre: Genre): Flow<Resource<List<Movie>>> {
        return object: NetworkBoundResource<List<Movie>, MoviesResponse>(){
            override suspend fun requestFromRemote(): Result<MoviesResponse> {
                return remoteDataSource.fetchMovieByGenre(genre.id.toString())
            }

            override fun loadResult(responseData: MoviesResponse): Flow<List<Movie>> {
                return flowOf(
                    responseData.toMovies()
                )
            }

            override suspend fun onSuccess(responseData: MoviesResponse) {
                super.onSuccess(responseData)
                // TODO: Store to local
            }

        }.asFlow()
    }

}