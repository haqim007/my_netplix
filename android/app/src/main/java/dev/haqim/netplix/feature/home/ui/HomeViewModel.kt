package dev.haqim.netplix.feature.home.ui

import dev.haqim.netplix.core.data.mechanism.Resource
import dev.haqim.netplix.core.domain.model.Genre
import dev.haqim.netplix.core.domain.model.Movie
import dev.haqim.netplix.core.ui.BaseViewModel
import dev.haqim.netplix.feature.home.domain.usecase.GetLatestMoviesUseCase
import dev.haqim.netplix.feature.home.domain.usecase.GetMovieGenresUseCase
import dev.haqim.netplix.feature.home.domain.usecase.GetMoviesByGenreUseCase
import dev.haqim.netplix.feature.home.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getLatestMoviesUseCase: GetLatestMoviesUseCase,
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase,
    private val getMovieGenresUseCase: GetMovieGenresUseCase,
) : BaseViewModel<HomeUiState, HomeUiAction>() {
    override val _state = MutableStateFlow<HomeUiState>(HomeUiState())
    override fun doAction(action: HomeUiAction) {
        when(action){
            HomeUiAction.GetLatestMovies -> {
                getLatestMoviesUseCase().launchCollect {
                    _state.update { state ->
                        state.copy(latestMovies = it)
                    }
                }
            }
            HomeUiAction.GetMovieGenres -> {
                getMovieGenresUseCase().launchCollect {
                    _state.update { state ->
                        state.copy(movieGenres = it)
                    }
                }
            }
            HomeUiAction.GetPopularMovies -> {
                getPopularMoviesUseCase().launchCollect {
                    _state.update { state ->
                        state.copy(popularMovies = it)
                    }
                }
            }

            is HomeUiAction.GetMoviesByGenre -> {
                getMoviesByGenreUseCase(action.genre).launchCollect {
                    if (it is Resource.Success){
                        _state.update { state ->
                            val newData = state.genresWithMovies.toMutableMap()
                            newData[action.genre.id] = it.data ?: listOf()
                            state.copy(
                                genresWithMovies = newData
                            )
                        }
                    }
                }
            }
        }
    }
}

typealias GenreId = Int

data class HomeUiState(
    val popularMovies: Resource<List<Movie>> = Resource.Idle(),
    val latestMovies: Resource<List<Movie>> = Resource.Idle(),
    val movieGenres: Resource<List<Genre>> = Resource.Idle(),
    val genresWithMovies: Map<GenreId, List<Movie>> = mapOf()
)

sealed class HomeUiAction{
    data object GetPopularMovies: HomeUiAction()
    data object GetLatestMovies: HomeUiAction()
    data object GetMovieGenres: HomeUiAction()
    data class GetMoviesByGenre(val genre: Genre): HomeUiAction()
}

