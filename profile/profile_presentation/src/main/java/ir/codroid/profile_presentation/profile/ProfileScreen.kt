package ir.codroid.profile_presentation.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ir.codroid.core.R
import ir.codroid.core.domain.model.ShopInfo
import ir.codroid.profile_presentation.profile.component.ProfileHeader
import ir.codroid.profile_presentation.profile.component.ProfileItem

@Composable
fun ProfileScreen(
    onProfileEditClick: () -> Unit,
    onThemeClick: () -> Unit,
    onLanguageClick: () -> Unit,
    onRateClick: () -> Unit,
    onAboutClick: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val shopInfo = viewModel.state.shopInfo
    ProfileScreenContent(
        shopInfo = shopInfo,
        onProfileEditClick = onProfileEditClick,
        onThemeClick = onThemeClick,
        onRateClick = onRateClick,
        onAboutClick = onAboutClick,
        onLanguageClick = onLanguageClick
    )
}

@Composable
private fun ProfileScreenContent(
    shopInfo: ShopInfo,
    onProfileEditClick: () -> Unit,
    onThemeClick: () -> Unit,
    onRateClick: () -> Unit,
    onAboutClick: () -> Unit,
    onLanguageClick: () -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            ProfileHeader(
                shopInfo = shopInfo,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp)
            )
            ProfileItem(
                name = stringResource(id = R.string.tit_shop_info),
                description = stringResource(id = R.string.sbt_shop_info),
                icon = Icons.Outlined.Person,
                modifier = Modifier.fillMaxWidth()
            ) {
                onProfileEditClick()
            }
            ProfileItem(
                name = stringResource(id = R.string.tit_theme),
                description = stringResource(id = R.string.sbt_theme),
                icon = Icons.Outlined.Home,
                modifier = Modifier.fillMaxWidth()
            ) {
                onThemeClick()
            }

            ProfileItem(
                name = stringResource(id = R.string.tit_language),
                description = stringResource(id = R.string.sbt_language),
                icon = Icons.Outlined.Settings,
                modifier = Modifier.fillMaxWidth()
            ) {
                onThemeClick()
            }

            ProfileItem(
                name = stringResource(id = R.string.tit_rate),
                description = stringResource(id = R.string.sbt_rate),
                icon = Icons.Outlined.Star,
                modifier = Modifier.fillMaxWidth()
            ) {
                onRateClick()
            }
            ProfileItem(
                name = stringResource(id = R.string.tit_about_us),
                description = stringResource(id = R.string.sbt_about_us),
                icon = Icons.Outlined.Info,
                modifier = Modifier.fillMaxWidth()
            ) {
                onAboutClick()
            }

        }
    }
}

@Preview
@Composable
private fun ProfileScreenContentPreview() {
    ProfileScreenContent(
        shopInfo = ShopInfo(
            shopName = "Mobile Amir",
            shopDescription = "Avalin Mobile Iran"
        ),
        onAboutClick = {},
        onProfileEditClick = {},
        onRateClick = {},
        onThemeClick = {},
        onLanguageClick = {},
    )
}