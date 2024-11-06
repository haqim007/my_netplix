package dev.haqim.netplix.core.ui.component.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.haqim.netplix.R
import dev.haqim.netplix.core.ui.component.LottieLoader
import dev.haqim.netplix.core.ui.theme.NetplixTheme


@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    message: String?,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge.copy(
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center
    ),
    onTryAgain: (() -> Unit)? = null,
    onSecondaryAction: (@Composable () -> Unit)? = null,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieLoader(
            jsonRaw = R.raw.lottie_error,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .size(200.dp)
        )
        Text(
            text = stringResource(R.string.oops_error_occurred),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.error
            )
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = message ?: stringResource(R.string.unknown_error),
            style = textStyle,
            maxLines = 3
        )
        if (onTryAgain != null){
            Button(
                modifier = Modifier
                    .padding(top = 25.dp, bottom = 6.dp)
                    .wrapContentWidth(),
                onClick = { onTryAgain() },
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(5.dp),
            ) {
                Text(
                    text = stringResource(R.string.try_again),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        if (onSecondaryAction != null){
            onSecondaryAction()
        }
    }
}

@Preview(apiLevel = 34)
@Composable
private fun ErrorView_Preview(){
    NetplixTheme {
        ErrorView(
            message = "Error nihh",
            onTryAgain = {}
        ) {
            TextButton(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 6.dp)
                    .wrapContentWidth(),
                onClick = {},
            ) {
                Text(
                    text = stringResource(R.string.try_again),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}