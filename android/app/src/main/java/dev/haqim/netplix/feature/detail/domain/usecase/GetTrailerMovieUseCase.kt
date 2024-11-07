package dev.haqim.netplix.feature.detail.domain.usecase

import dev.haqim.netplix.core.data.mechanism.Resource
import dev.haqim.netplix.core.domain.model.Movie
import dev.haqim.netplix.feature.detail.domain.repository.IDetailMovieRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetTrailerMovieUseCase: KoinComponent {
    private val repository: IDetailMovieRepository by inject()
    operator fun invoke(movie: Movie): Flow<Resource<Movie>>{
        return repository.getTrailer(movie)
    }
}