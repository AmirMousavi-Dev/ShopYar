package ir.codroid.merchandise_presentation.merchandise_list.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import ir.codroid.core_ui.LocalSpacing
import ir.codroid.core_ui.R

@Composable
fun MerchandiseDefaultImage(
    imagePath: String?,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    val imageShape = RoundedCornerShape(spacing.spaceSmall)
    if (imagePath == null)
        Image(
            painter = painterResource(id = R.drawable.ic_merchandise),
            contentDescription = "",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer),
            modifier = modifier
                .clip(imageShape)
                .border(1.dp, MaterialTheme.colorScheme.onPrimaryContainer, imageShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentScale = ContentScale.Fit
        )
    else {
        Image(
            painter = rememberAsyncImagePainter(
                model = imagePath
            ),
            contentDescription = "",
            modifier = modifier
                .clip(imageShape),
            contentScale = ContentScale.FillBounds
        )


    }
}

@Preview
@Composable
private fun MerchandiseDefaultImagePreview() {
    MerchandiseDefaultImage(
        null
    )
}