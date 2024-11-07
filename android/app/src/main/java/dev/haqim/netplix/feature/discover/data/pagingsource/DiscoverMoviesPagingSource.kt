package dev.haqim.netplix.feature.discover.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.haqim.netplix.core.data.toMovies
import dev.haqim.netplix.core.domain.model.Movie
import dev.haqim.netplix.feature.discover.data.remote.DiscoverRemoteDataSource
import io.ktor.utils.io.errors.IOException


internal const val DEFAULT_LIMIT = 20
internal const val PAGE_START_KEY = 1

internal class DiscoverMoviesPagingSource(
    private val remoteDataSource: DiscoverRemoteDataSource,
    private val keyword: String
): PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: PAGE_START_KEY

        return try {
            val response = remoteDataSource.fetchMovieByKeyword(
                keyword,
                position
            )

            if (response.isSuccess) {
                val movies = response.getOrNull()?.toMovies() ?: listOf()
                val nextKey = if (movies.isEmpty() || movies.size < DEFAULT_LIMIT) {
                    null
                } else {
                    position + PAGE_START_KEY
                }
                LoadResult.Page(
                    data = movies,
                    prevKey = if (position == PAGE_START_KEY) null
                    else position - PAGE_START_KEY,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(
                   response.exceptionOrNull()
                       ?: Throwable(message = "Unknown error on pagingsource")
                )
            }

        }
        catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
        catch (exception: Exception) {
            exception.message ?: "Unknown error on pagingsource"
            return LoadResult.Error(exception)
        }
    }
}