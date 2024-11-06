package dev.haqim.netplix.feature.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.haqim.netplix.R
import dev.haqim.netplix.core.domain.model.Genre
import dev.haqim.netplix.core.domain.model.Movie
import dev.haqim.netplix.core.domain.model.dummyMovies
import dev.haqim.netplix.core.ui.theme.NetplixTheme
import dev.haqim.netplix.feature.detail.ui.ModalMovieDetail
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDiscover: (Genre?) -> Unit
) {

    val movies = dummyMovies
    val popularMovies = movies.subList(0, 6)

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
                PopularSliderView(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    popularMovies = popularMovies,
                    onClick = { movie ->
                        openedMovie = movie
                        showBottomSheet = true
                    }
                )
            }

            item {
                MoviesSectionView(
                    title = stringResource(R.string.latest),
                    movies = movies,
                    onClick = { movie ->
                        openedMovie = movie
                        showBottomSheet = true
                    }
                )
            }

            item {
                MoviesSectionView(
                    title = stringResource(R.string.action),
                    movies = movies,
                    isPerCategory = true,
                    onClick = { movie ->
                        openedMovie = movie
                        showBottomSheet = true
                    }
                )
            }

            item {
                MoviesSectionView(
                    title = stringResource(R.string.comedy),
                    movies = movies,
                    isPerCategory = true,
                    onClick = { movie ->
                        openedMovie = movie
                        showBottomSheet = true
                    }
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
