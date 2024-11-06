package dev.haqim.netplix.feature.home.ui

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.haqim.netplix.R
import dev.haqim.netplix.core.domain.model.Movie
import dev.haqim.netplix.core.domain.model.dummyMovies
import dev.haqim.netplix.core.ui.theme.NetplixTheme


@Composable
fun PopularMovieCard(
    modifier: Modifier = Modifier,
    movie: Movie
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.posterUrl)
                    .crossfade(true)
                    .build(),
                placeholder = if (isSystemInDarkTheme()){
                    painterResource(R.drawable.baseline_play_circle_24_white)
                } else {
                    painterResource(R.drawable.baseline_play_circle_24)
                },
                error = painterResource(id = R.drawable.error_alert),
                onError = {
                    Log.e("PopularMovieCard", it.result.throwable.message.toString())
                },
                contentDescription = stringResource(R.string.poster_of_, movie.title),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .weight(1f)
            )
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 8.dp),
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
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
private fun PopularMovieCardPreview() {
    NetplixTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            PopularMovieCard(
                movie = dummyMovies[0]
            )
        }
    }
}

