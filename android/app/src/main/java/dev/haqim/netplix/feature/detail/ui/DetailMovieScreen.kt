package dev.haqim.netplix.feature.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
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
import dev.haqim.netplix.core.domain.model.Movie
import dev.haqim.netplix.core.domain.model.dummyMovies
import dev.haqim.netplix.core.ui.component.MyIconButton
import dev.haqim.netplix.core.ui.component.VideoPlayerView
import dev.haqim.netplix.core.ui.component.YouTubePlayerView
import dev.haqim.netplix.core.ui.component.error.ErrorView
import dev.haqim.netplix.core.ui.theme.NetplixTheme
import dev.haqim.netplix.feature.home.ui.MoviesSectionView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMovieScreen(
    movie: Movie,
    close: () -> Unit,
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        MyIconButton(
            onClick = {
                close()
            },
            modifier = Modifier
                .align(Alignment.End)
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
        Column(
            modifier = Modifier.weight(1f).padding(horizontal = 8.dp, vertical = 16.dp)
        ) {
            if (movie.trailer?.url == null || LocalInspectionMode.current){
                ErrorView(
                    modifier = Modifier.requiredHeightIn(max = 250.dp),
                    message = stringResource(R.string.trailer_unavailable)
                )
            }
            else{
                if (movie.trailer.isYoutube){
                    YouTubePlayerView(
                        modifier = Modifier.requiredHeightIn(max = 250.dp),
                        videoId = movie.trailer.key
                    )
                }else{
                    VideoPlayerView(
                        modifier = Modifier.requiredHeightIn(max = 250.dp),
                        uri = movie.trailer.url
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    movie.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    movie.overview,
                    fontSize = 14.sp,
                    overflow = TextOverflow.Ellipsis
                )
            }

            MoviesSectionView(
                modifier = Modifier,
                title = stringResource(R.string.latest),
                movies = dummyMovies,
                onClick = { movie ->
                    scope.launch {
                        openedMovie = movie
                        showBottomSheet = true
                    }
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

@Preview
@Composable
private fun DetailMovieScreenPreview(){
    NetplixTheme {
        Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
            DetailMovieScreen(
                movie = dummyMovies[0],
                close = {},
            )
        }
    }
}