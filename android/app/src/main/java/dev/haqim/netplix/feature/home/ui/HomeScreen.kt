package dev.haqim.netplix.feature.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.haqim.netplix.R
import dev.haqim.netplix.core.data.mechanism.Resource
import dev.haqim.netplix.core.domain.model.Genre
import dev.haqim.netplix.core.domain.model.Movie
import dev.haqim.netplix.core.ui.component.error.EmptyView
import dev.haqim.netplix.core.ui.component.error.ErrorView
import dev.haqim.netplix.core.ui.theme.NetplixTheme
import dev.haqim.netplix.feature.detail.ui.ModalMovieDetail
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
    navigateToDiscover: (Genre?) -> Unit
) {

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    val scope = rememberCoroutineScope()
    var showBottomSheet by rememberSaveable {
        mutableStateOf(false)
    }
    var openedMovie: Movie? by remember{
        mutableStateOf(null)
    }

    val state = viewModel.state.collectAsState()
    val action = {action: HomeUiAction -> viewModel.doAction(action)}
    val popularMovies = state.value.popularMovies
    val latestMovies = state.value.latestMovies
    val movieGenres = state.value.movieGenres

    LaunchedEffect(key1 = Unit){
        action(HomeUiAction.GetPopularMovies)
        delay(500)
        action(HomeUiAction.GetLatestMovies)
        delay(500)
        action(HomeUiAction.GetMovieGenres)
    }
    
    Scaffold(
        modifier = modifier,
        topBar = {
            HomeTopBar(navigateToDiscover = {
                navigateToDiscover(null)
            })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            item {
                when(popularMovies){
                    is Resource.Error -> {
                        ErrorView(
                            modifier = Modifier.fillMaxWidth().height(200.dp),
                            message = stringResource(R.string.oops_error_occurred),
                            onTryAgain = {
                                action(HomeUiAction.GetPopularMovies)
                            }
                        )
                    }
                    is Resource.Success -> {
                        if (popularMovies.data?.isNotEmpty() == true){
                            PopularSliderView(
                                modifier = Modifier
                                    .padding(top = 8.dp),
                                popularMovies = popularMovies.data,
                                onClick = { movie ->
                                    openedMovie = movie
                                    showBottomSheet = true
                                }
                            )
                        }
                        else{
                            EmptyView(
                                message = stringResource(R.string.oops_empty),
                                onTryAgain = {
                                    action(HomeUiAction.GetPopularMovies)
                                },
                                lottieSize = 150.dp
                            )
                        }
                    }
                    else -> {
                        Box(
                            modifier = Modifier.fillMaxWidth().height(150.dp),
                            contentAlignment = Alignment.Center
                        ){
                            CircularProgressIndicator()
                        }
                    }
                }
            }

            item {
                when(latestMovies){
                    is Resource.Error -> {
                        ErrorView(
                            modifier = Modifier.fillMaxWidth().height(200.dp),
                            message = stringResource(R.string.oops_error_occurred),
                            onTryAgain = {
                                action(HomeUiAction.GetPopularMovies)
                            }
                        )
                    }
                    is Resource.Success -> {
                        if (latestMovies.data?.isNotEmpty() == true){
                            LatestMoviesSectionView(
                                title = stringResource(R.string.latest),
                                movies = latestMovies.data,
                                onClick = { movie ->
                                    openedMovie = movie
                                    showBottomSheet = true
                                }
                            )
                        }
                        else{
                            EmptyView(
                                message = stringResource(R.string.oops_empty),
                                onTryAgain = {
                                    action(HomeUiAction.GetPopularMovies)
                                },
                                lottieSize = 150.dp
                            )
                        }
                    }
                    else -> {
                        Box(
                            modifier = Modifier.fillMaxWidth().height(150.dp),
                            contentAlignment = Alignment.Center
                        ){
                            CircularProgressIndicator()
                        }
                    }
                }
            }

            if (movieGenres is Resource.Success){
                items(movieGenres.data?.size ?: 0){index ->
                    val genre = movieGenres.data!![index]
                    MoviesGenreSectionView(
                        title = genre.name,
                        onLoad = {
                            action(HomeUiAction.GetMoviesByGenre(genre))
                        },
                        movies = state.value.genresWithMovies[genre.id] ?: listOf(),
                        onClick = { movie ->
                            openedMovie = movie
                            showBottomSheet = true
                        }
                    )
                }
            }
        }

        if (showBottomSheet && openedMovie != null){
            ModalMovieDetail(openedMovie!!, sheetState){
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        showBottomSheet = false
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun HomeScreenPreview() {
    NetplixTheme {
        HomeScreen(
            navigateToDiscover = {}
        )
    }
}
