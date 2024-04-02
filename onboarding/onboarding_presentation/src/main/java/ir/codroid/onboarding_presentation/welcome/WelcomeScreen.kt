package ir.codroid.onboarding_presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.codroid.core_ui.LocalSpacing
import ir.codroid.core_ui.R
import ir.codroid.core_ui.component.CircularImage
import ir.codroid.core_ui.component.DefaultButton

@Composable
fun WelcomeScreen(
    onNextClick :() -> Unit
) {

    val spacing = LocalSpacing.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularImage(
            imageDrawable = R.drawable.shop,
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            iconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.size(320.dp),
            imageSize = 200.dp
        )
        Spacer(modifier = Modifier.height(spacing.spaceLarge))

        Text(
            text = stringResource(id = ir.codroid.core.R.string.app_name),
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        Text(
            text = stringResource(id = ir.codroid.core.R.string.cpt_about_app),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(spacing.spaceLarge)
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        DefaultButton(text = "next" , modifier = Modifier.height(60.dp).width(128.dp) ,
            textStyle = MaterialTheme.typography.titleMedium.copy(fontSize = 24.sp)) {
            onNextClick()
        }


    }
}

@Preview
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen(){}
}