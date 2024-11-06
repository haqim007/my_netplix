package dev.haqim.netplix.feature.home.domain.usecase

import dev.haqim.netplix.core.data.mechanism.Resource
import dev.haqim.netplix.core.domain.model.Genre
import dev.haqim.netplix.core.domain.model.Movie
import dev.haqim.netplix.feature.home.domain.repository.IHomeRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMoviesByGenreUseCase: KoinComponent {
    private val repository: IHomeRepository by inject()
    operator fun invoke(genre: Genre): Flow<Resource<List<Movie>>>{
        return repository.fetchMoviesByGenre(genre)
    }
}