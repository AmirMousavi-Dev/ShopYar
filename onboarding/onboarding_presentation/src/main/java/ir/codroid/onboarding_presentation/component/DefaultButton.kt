package ir.codroid.onboarding_presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DefaultButton(
    text: String,
    modifier: Modifier = Modifier
        .height(50.dp)
        .width(90.dp),
    enabled: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge,
    onclick: () -> Unit
) {
    Button(
        onClick = onclick,
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text = text, style = textStyle)
    }
}

@Preview
@Composable
private fun DefaultButtonPreview() {
    Column {

        DefaultButton("next") {}
        DefaultButton("next", enabled = false) {}
    }
}