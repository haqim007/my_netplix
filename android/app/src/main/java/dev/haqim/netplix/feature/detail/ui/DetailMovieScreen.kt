package dev.haqim.netplix.feature.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.haqim.netplix.R
import dev.haqim.netplix.core.data.mechanism.Resource
import dev.haqim.netplix.core.domain.model.Movie
import dev.haqim.netplix.core.domain.model.MovieTrailer
import dev.haqim.netplix.core.domain.model.dummyMovies
import dev.haqim.netplix.core.ui.KoinPreviewWrapper
import dev.haqim.netplix.core.ui.component.LoadingView
import dev.haqim.netplix.core.ui.component.MyIconButton
import dev.haqim.netplix.core.ui.component.VideoPlayerView
import dev.haqim.netplix.core.ui.component.YouTubePlayerView
import dev.haqim.netplix.core.ui.component.error.EmptyView
import dev.haqim.netplix.core.ui.component.error.ErrorView
import dev.haqim.netplix.core.ui.theme.NetplixTheme
import dev.haqim.netplix.di.allModules
import dev.haqim.netplix.feature.home.ui.LatestMoviesSectionView
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMovieScreen(
    viewModel: DetailMovieViewModel = koinViewModel<DetailMovieViewModel>(),
    movie: Movie,
    close: () -> Unit,
) {

    val state by viewModel.state.collectAsState()
    val action = {action: DetailMovieUiAction -> viewModel.doAction(action)}
    val loadMovie = {action(DetailMovieUiAction.GetTrailerMovie(movie))}

    LaunchedEffect(key1 = Unit){
        loadMovie()
        action(DetailMovieUiAction.GetLatestMovies)
    }

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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        item{
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                MyIconButton(
                    onClick = {
                        close()
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(20.dp),
                    colors = IconButtonDefaults.iconButtonColors().copy(
                        containerColor = MaterialTheme.colorScheme.onBackground
                    )
                ) {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background
                    )
                }
            }
        }

        item {
            DetailMovieContent(
                modifier = Modifier/*.weight(1f)*/
                    .padding(horizontal = 8.dp)
                    .padding(top = 24.dp),
                state,
                onLatestMovieClick = { movie ->
                    openedMovie = movie
                    showBottomSheet = true
                },
                onGetLatestMovies = {
                    action(DetailMovieUiAction.GetLatestMovies)
                },
                loadMovie = loadMovie
            )
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

@Composable
private fun DetailMovieContent(
    modifier: Modifier = Modifier,
    state: DetailMovieUiState,
    onLatestMovieClick: (Movie) -> Unit,
    onGetLatestMovies: () -> Unit,
    loadMovie: () -> Unit
) {
    when (state.movie) {
        is Resource.Success -> {
            val movieWithTrailer = state.movie.data!!
            Column(
                modifier = modifier,
            ) {
                if (movieWithTrailer.trailer?.url == null || LocalInspectionMode.current) {
                    ErrorView(
                        modifier = Modifier.requiredHeightIn(max = 250.dp),
                        message = stringResource(R.string.trailer_unavailable)
                    )
                } else {
                    if (movieWithTrailer.trailer.isYoutube) {
                        YouTubePlayerView(
                            modifier = Modifier.requiredHeightIn(max = 250.dp),
                            videoId = movieWithTrailer.trailer.key
                        )
                    } else {
                        VideoPlayerView(
                            modifier = Modifier.requiredHeightIn(max = 250.dp),
                            uri = movieWithTrailer.trailer.url
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        movieWithTrailer.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        movieWithTrailer.overview,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                LatestMovieInDetail(
                    latestMovies = state.latestMovies,
                    onTryAgain = onGetLatestMovies,
                    onClick = onLatestMovieClick
                )

            }
        }

        is Resource.Error -> {
            ErrorView(
                modifier = modifier,
                message = stringResource(R.string.oops_error_occurred),
                onTryAgain = loadMovie
            )
        }

        else -> {
            LoadingView(
                modifier = modifier,
            )
        }
    }
}

@Composable
private fun LatestMovieInDetail(
    latestMovies: Resource<List<Movie>>,
    onTryAgain: () -> Unit,
    onClick: (Movie) -> Unit
) {
    when (latestMovies) {
        is Resource.Error -> {
            ErrorView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                message = stringResource(R.string.oops_error_occurred),
                onTryAgain = onTryAgain
            )
        }

        is Resource.Success -> {
            if (latestMovies.data?.isNotEmpty() == true) {
                LatestMoviesSectionView(
                    title = stringResource(R.string.latest),
                    movies = latestMovies.data,
                    onClick = { movie ->
                        onClick(movie)
                    }
                )
            } else {
                EmptyView(
                    message = stringResource(R.string.oops_empty),
                    onTryAgain = onTryAgain,
                    lottieSize = 150.dp
                )
            }
        }

        else -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview
@Composable
private fun DetailMovieScreenPreview(){
    KoinPreviewWrapper(
        allModules
    ){
        NetplixTheme {
            Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
                DetailMovieScreen(
                    movie = dummyMovies[0],
                    close = {},
                )
            }
        }
    }
}