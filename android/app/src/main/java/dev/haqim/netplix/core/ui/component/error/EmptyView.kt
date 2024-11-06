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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.haqim.netplix.R
import dev.haqim.netplix.core.ui.component.LottieLoader
import dev.haqim.netplix.core.ui.theme.NetplixTheme


@Composable
fun EmptyView(
    modifier: Modifier = Modifier,
    message: String?,
    onTryAgain: (() -> Unit)? = null,
    onSecondaryAction: (@Composable () -> Unit)? = null,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieLoader(
            jsonRaw = R.raw.lottie_empty,
            modifier = Modifier
                .padding(bottom = 14.dp)
                .size(250.dp),
            iterations = 3
        )

        Text(
            text = stringResource(R.string.attention_),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.error
            ),
            modifier = Modifier
                .padding(bottom = 16.dp)
        )

        Text(
            text = message ?: stringResource(R.string.unknown_error),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        if (onTryAgain != null) {
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
                    text = stringResource(R.string.please_try_again),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        if (onSecondaryAction != null){
            onSecondaryAction()
        }
    }
}

@Preview
@Composable
private fun ErrorView_Preview(){
    NetplixTheme {
        EmptyView(message = "Error nihh", onTryAgain = {})
    }
}