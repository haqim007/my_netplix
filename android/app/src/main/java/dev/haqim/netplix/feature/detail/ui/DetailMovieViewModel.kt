package dev.haqim.netplix.feature.detail.ui

import dev.haqim.netplix.core.data.mechanism.Resource
import dev.haqim.netplix.core.domain.model.Movie
import dev.haqim.netplix.core.ui.BaseViewModel
import dev.haqim.netplix.feature.detail.domain.usecase.GetTrailerMovieUseCase
import dev.haqim.netplix.feature.home.domain.usecase.GetLatestMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class DetailMovieViewModel(
    private val getTrailerMovieUseCase: GetTrailerMovieUseCase,
    private val getLatestMoviesUseCase: GetLatestMoviesUseCase
) : BaseViewModel<DetailMovieUiState, DetailMovieUiAction>() {
    override val _state = MutableStateFlow<DetailMovieUiState>(DetailMovieUiState())
    override fun doAction(action: DetailMovieUiAction) {
        when(action){
            DetailMovieUiAction.GetLatestMovies -> {
                getLatestMoviesUseCase().launchCollect {
                    _state.update { state ->
                        state.copy(latestMovies = it)
                    }
                }
            }

            is DetailMovieUiAction.GetTrailerMovie -> {
                getTrailerMovieUseCase(action.movie).launchCollect {
                    _state.update { state ->
                        state.copy(movie = it)
                    }
                }
            }
        }
    }
}


data class DetailMovieUiState(
    val latestMovies: Resource<List<Movie>> = Resource.Idle(),
    val movie: Resource<Movie> = Resource.Idle(),
)

sealed class DetailMovieUiAction{
    data object GetLatestMovies: DetailMovieUiAction()
    data class GetTrailerMovie(val movie: Movie): DetailMovieUiAction()
}

