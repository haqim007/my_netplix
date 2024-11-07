package dev.haqim.netplix.feature.detail.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.haqim.netplix.core.domain.model.Movie

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ModalMovieDetail(
    movie: Movie,
    sheetState: SheetState,
    onClose: () -> Unit
) {
    ModalBottomSheet(
        modifier = Modifier
            .padding(top = 20.dp)
            .padding(horizontal = 8.dp),
        sheetState = sheetState,
        onDismissRequest = onClose,
        shape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp
        ),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        DetailMovieScreen(
            movie = movie,
            close = onClose
        )
    }
}