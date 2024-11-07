package dev.haqim.netplix.feature.detail.domain.repository

import dev.haqim.netplix.core.data.mechanism.Resource
import dev.haqim.netplix.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IDetailMovieRepository {
    fun getTrailer(movie: Movie): Flow<Resource<Movie>>
}