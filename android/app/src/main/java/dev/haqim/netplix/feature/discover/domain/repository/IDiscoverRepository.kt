package dev.haqim.netplix.feature.discover.domain.repository

import androidx.paging.PagingData
import dev.haqim.netplix.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IDiscoverRepository {
    fun discoverMovies(keyword: String = ""): Flow<PagingData<Movie>>
}