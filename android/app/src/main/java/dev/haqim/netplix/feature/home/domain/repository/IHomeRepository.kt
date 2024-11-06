package dev.haqim.netplix.feature.home.domain.repository

import dev.haqim.netplix.core.data.mechanism.Resource
import dev.haqim.netplix.core.domain.model.Genre
import dev.haqim.netplix.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {
    fun fetchPopularMovies(): Flow<Resource<List<Movie>>>

    fun fetchNowPlayingMovies(): Flow<Resource<List<Movie>>>

    fun fetchMovieGenres() : Flow<Resource<List<Genre>>>

    fun fetchMoviesByGenre(genre: Genre) : Flow<Resource<List<Movie>>>
}