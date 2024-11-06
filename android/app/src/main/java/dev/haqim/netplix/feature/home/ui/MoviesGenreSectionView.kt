package dev.haqim.netplix.feature.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import dev.haqim.netplix.core.domain.model.Movie
import dev.haqim.netplix.core.ui.component.MovieCard
import dev.haqim.netplix.core.ui.theme.fontMontaserrat

@Composable
fun MoviesGenreSectionView(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    title: String,
    onLoad: () -> Unit,
    onClick: (Movie) -> Unit,
) {

    LaunchedEffect(key1 = Unit) {
        onLoad()
    }

    if (movies.isNotEmpty()){
        Column(
            modifier = modifier
                .fillMaxWidth(),
        ) {
            Text(
                title,
                fontFamily = fontMontaserrat,
                fontWeight = FontWeight.SemiBold
            )
            LazyRow(
                modifier = Modifier
            ) {
                items(movies.size) { index ->
                    val movie = movies[index]
                    MovieCard(onClick, movie, true, title)
                }
            }
        }
    }
}

