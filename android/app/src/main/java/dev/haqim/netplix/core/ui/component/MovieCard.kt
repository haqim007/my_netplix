package dev.haqim.netplix.core.ui.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.haqim.netplix.R
import dev.haqim.netplix.core.domain.model.Movie

@Composable
fun MovieCard(
    onClick: (Movie) -> Unit,
    movie: Movie,
    isPerCategory: Boolean,
    title: String
) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onClick(movie)
            },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = if (isPerCategory) Alignment.Start
            else Alignment.CenterHorizontally
        ) {
            if (isPerCategory) {
                Text(
                    movie.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(8.dp)
                )
            }

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.posterUrl)
                    .crossfade(true)
                    .build(),
                placeholder = if (isSystemInDarkTheme()) {
                    painterResource(R.drawable.baseline_play_circle_24_white)
                } else {
                    painterResource(R.drawable.baseline_play_circle_24)
                },
                error = painterResource(id = R.drawable.error_alert),
                onError = {
                    Log.e(
                        "HomeSectionView $title",
                        it.result.throwable.message.toString()
                    )
                },
                contentDescription = stringResource(R.string.poster_of_, movie.title),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeightIn(min = 200.dp)
            )
            if (!isPerCategory) {
                Text(
                    movie.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}