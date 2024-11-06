package dev.haqim.netplix.core.ui.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.More
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.haqim.netplix.R


@Composable
fun MyIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .size(20.dp),
    enabled: Boolean = true,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    IconButton(
        colors = colors,
        modifier = modifier,
        onClick = onClick,
        content = content,
        enabled = enabled,
        interactionSource = interactionSource
    )
}

@Preview
@Composable
private fun MyIconButtonPreview() {
    MyIconButton(
        onClick = {},
        content = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.More,
                contentDescription = stringResource(id = R.string.see_more)
            )
        }
    )
}

