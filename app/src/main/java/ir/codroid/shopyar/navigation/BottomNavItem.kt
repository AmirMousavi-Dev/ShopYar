package ir.codroid.shopyar.navigation

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import ir.codroid.core.R
import ir.codroid.core.util.UiText

class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
) {

    //function to get the list of bottomNavigationItems
    companion object {

    fun bottomNavigationItems(context: Context): List<BottomNavItem> {
        return listOf(
            BottomNavItem(
                label = UiText.StringResource(R.string.factor).asString(context),
                icon = Icons.Filled.Home,
                route = Route.FACTOR
            ),
            BottomNavItem(
                label = UiText.StringResource(R.string.merchandise).asString(context),
                icon = Icons.Filled.ShoppingCart,
                route = Route.MERCHANDISE
            ),
            BottomNavItem(
                label = UiText.StringResource(R.string.profile).asString(context),
                icon = Icons.Filled.AccountCircle,
                route = Route.PROFILE
            ),
        )
    }
    }
}
