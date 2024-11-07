package dev.haqim.netplix.feature.discover.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.haqim.netplix.core.domain.model.Genre
import dev.haqim.netplix.core.domain.model.Movie
import dev.haqim.netplix.core.util.DispatcherProvider
import dev.haqim.netplix.feature.discover.data.pagingsource.DEFAULT_LIMIT
import dev.haqim.netplix.feature.discover.data.pagingsource.DiscoverMoviesPagingSource
import dev.haqim.netplix.feature.discover.data.pagingsource.PAGE_START_KEY
import dev.haqim.netplix.feature.discover.data.remote.DiscoverRemoteDataSource
import dev.haqim.netplix.feature.discover.domain.repository.IDiscoverRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn


class DiscoverRepository(
    private val dispatcher: DispatcherProvider,
    private val remoteDataSource: DiscoverRemoteDataSource
): IDiscoverRepository {
    override fun discoverMovies(keyword: String): Flow<PagingData<Movie>> {
        return Pager(
            initialKey = PAGE_START_KEY,
            config = PagingConfig(
                pageSize = DEFAULT_LIMIT
            ),
            pagingSourceFactory = {
                DiscoverMoviesPagingSource(
                    remoteDataSource = remoteDataSource,
                    keyword = keyword
                )
            }
        )
            .flow
            .flowOn(dispatcher.io)
    }

}