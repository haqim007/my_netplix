package dev.haqim.netplix.feature.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.haqim.netplix.core.domain.model.Movie
import dev.haqim.netplix.core.domain.model.dummyMovies
import dev.haqim.netplix.core.ui.theme.NetplixTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun PopularSliderView(
    modifier: Modifier = Modifier,
    popularMovies: List<Movie>,
    onClick: (Movie) -> Unit
) {
    var runSlides by remember {
        mutableStateOf(true)
    }

    val pagerState = rememberPagerState(
        pageCount = {
            popularMovies.size
        }
    )

    LaunchedEffect(key1 = pagerState.pageCount) {
        while (runSlides) {
            delay(5000)
            // Exit the loop if the coroutine is cancelled
            if (!isActive) {
                runSlides = false
                break
            }
            if (pagerState.pageCount == 0) {
                runSlides = false
                break
            } // break if pageCount reset to 0
            pagerState.animateScrollToPage(
                if (pagerState.currentPage < pagerState.pageCount - 1) {
                    pagerState.currentPage + 1
                } else {
                    0
                }
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(state = pagerState) { page ->
            // Our page content
            PopularMovieCard(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(175.dp)
                    .clickable {
                        onClick(popularMovies[page])
                    },
                movie = popularMovies[page]
            )
        }
        PagerDotIndicator(pagerState)
    }
}

@Preview
@Composable
private fun PopularSliderPreview(){
    NetplixTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            PopularSliderView(
                popularMovies = dummyMovies,
                onClick = { movie ->

                }
            )
        }
    }
}