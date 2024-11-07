package dev.haqim.netplix.feature.discover.domain.usecase

import androidx.paging.PagingData
import dev.haqim.netplix.core.domain.model.Genre
import dev.haqim.netplix.core.domain.model.Movie
import dev.haqim.netplix.feature.discover.domain.repository.IDiscoverRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DiscoverMoviesUseCase: KoinComponent {
    private val repository: IDiscoverRepository by inject()
    operator fun invoke(keyword: String): Flow<PagingData<Movie>> {
        return repository.discoverMovies(keyword)
    }
}