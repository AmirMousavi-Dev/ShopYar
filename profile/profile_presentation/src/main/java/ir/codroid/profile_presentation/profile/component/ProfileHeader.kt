package ir.codroid.profile_presentation.profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.codroid.core.domain.model.ShopInfo
import ir.codroid.core_ui.LocalSpacing
import ir.codroid.core_ui.R

@Composable
fun ProfileHeader(
    shopInfo: ShopInfo,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            if (shopInfo.shopImage == null) {
                Image(
                    painter = painterResource(id = R.drawable.shop),
                    contentDescription = shopInfo.shopName,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer),
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape)
                        .background(color = MaterialTheme.colorScheme.primaryContainer),
                    contentScale = ContentScale.Fit
                )

            } else {
                Image(
                    bitmap = shopInfo.shopImage!!.asImageBitmap(),
                    contentDescription = shopInfo.shopName,
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape)
                        .background(color = MaterialTheme.colorScheme.primaryContainer),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(spacing.spaceSmall))

            Text(
                text = shopInfo.shopName ?: stringResource(id = ir.codroid.core.R.string.profile),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Text(
                text = shopInfo.shopDescription
                    ?: stringResource(id = ir.codroid.core.R.string.profile),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview
@Composable
private fun ProfileHeaderPreview() {
    ProfileHeader(
        ShopInfo(
            shopName = "Mobile Amir",
            shopDescription = "Avalin Mobile Iran"
        )
    )
}