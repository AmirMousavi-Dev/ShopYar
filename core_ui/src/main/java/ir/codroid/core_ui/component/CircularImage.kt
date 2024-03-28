package ir.codroid.core_ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.codroid.core_ui.LocalSpacing

@Composable
fun CircularImage(
    imageVector: ImageVector,
    backgroundColor: Color,
    iconColor: Color,
    modifier: Modifier = Modifier,
    imageSize: Dp = 24.dp,
    contentDescription: String? = null,
) {
    val spacing = LocalSpacing.current
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .padding(spacing.spaceSmall),
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = imageVector,
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(iconColor),
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(imageSize)
        )
    }
}

@Composable
fun CircularImage(
    @DrawableRes imageDrawable: Int,
    backgroundColor: Color,
    iconColor: Color,
    modifier: Modifier = Modifier,
    imageSize: Dp = 24.dp,
    contentDescription: String? = null,
) {
    val spacing = LocalSpacing.current
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .padding(spacing.spaceSmall),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageDrawable),
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(iconColor),
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(imageSize)
        )
    }
}

@Composable
fun CircularImage(
    bitmap: ImageBitmap,
    backgroundColor: Color,
    iconColor: Color,
    modifier: Modifier = Modifier,
    imageSize: Dp = 24.dp,
    contentDescription: String? = null,
) {
    val spacing = LocalSpacing.current
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .padding(spacing.spaceSmall),
        contentAlignment = Alignment.Center
    ) {
        Image(
            bitmap = bitmap,
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(iconColor),
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(imageSize)
        )
    }
}