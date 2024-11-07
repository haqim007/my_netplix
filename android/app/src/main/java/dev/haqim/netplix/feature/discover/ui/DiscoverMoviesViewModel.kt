package dev.haqim.netplix.feature.discover.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dev.haqim.netplix.core.data.mechanism.Resource
import dev.haqim.netplix.core.domain.model.Genre
import dev.haqim.netplix.core.domain.model.Movie
import dev.haqim.netplix.core.ui.BaseViewModel
import dev.haqim.netplix.feature.discover.domain.usecase.DiscoverMoviesUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class, FlowPreview::class)
class DiscoverMoviesViewModel(
    private val discoverMoviesUseCase: DiscoverMoviesUseCase
) : BaseViewModel<DiscoverMoviesUiState, DiscoverMoviesUiAction>() {
    override val _state = MutableStateFlow(DiscoverMoviesUiState())

    val pagingFlow: Flow<PagingData<Movie>>

    init {
        val keywordState = state.distinctUntilChangedBy { it.keyword }.map { it.keyword }

        pagingFlow = keywordState
            .debounce(1000)
            .flatMapLatest { keyword ->
                discoverMoviesUseCase(keyword)
            }
            .cachedIn(viewModelScope)

    }

    override fun doAction(action: DiscoverMoviesUiAction) {
        when(action){
            is DiscoverMoviesUiAction.FindByKeyword -> {
                _state.update { state ->
                    state.copy(
                        keyword = action.keyword
                    )
                }
            }
        }
    }
}


data class DiscoverMoviesUiState(
    val keyword: String = "",
)

sealed class DiscoverMoviesUiAction{
    data class FindByKeyword(
        val keyword: String
    ): DiscoverMoviesUiAction()
}

