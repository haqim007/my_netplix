package dev.haqim.netplix.core.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp

@Composable
fun StarRatings(rating: Double, size: Dp = 24.dp) {
    Row {
        for (i in 1..5) {
            when {
                i <= rating.toInt() -> {
                    // Full star
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = Color(0xFFB8860B), // Gold color for full star
                        modifier = Modifier.size(size)
                    )
                }
//                i == rating.toInt() + 1 && rating % 1 >= 0.5 -> {
//                    // Half star
//                    Icon(
//                        imageVector = Icons.Filled.Star,
//                        contentDescription = null,
//                        tint = Color(0xFFFFD700), // Gold color for half star
//                        modifier = Modifier.size(size)
//                    )
//                }
                else -> {
                    // Empty star
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        tint = Color(0xFFC0C0C0), // Gray color for empty star
                        modifier = Modifier.size(size)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStarRating() {
    StarRatings(rating = 3.5) // Change the rating here for different previews
}
