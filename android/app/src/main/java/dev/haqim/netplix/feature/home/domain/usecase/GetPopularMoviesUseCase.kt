package dev.haqim.netplix.feature.home.domain.usecase

import dev.haqim.netplix.core.data.mechanism.Resource
import dev.haqim.netplix.core.domain.model.Movie
import dev.haqim.netplix.feature.home.domain.repository.IHomeRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetPopularMoviesUseCase: KoinComponent {
    private val repository: IHomeRepository by inject()
    operator fun invoke(): Flow<Resource<List<Movie>>>{
        return repository.fetchPopularMovies()
    }
}