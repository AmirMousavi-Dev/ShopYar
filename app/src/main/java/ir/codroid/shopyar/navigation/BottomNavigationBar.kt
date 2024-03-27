package ir.codroid.shopyar.navigation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ir.codroid.core_ui.LocalSpacing

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    val context = LocalContext.current
    val spacing = LocalSpacing.current

    val items = BottomNavItem.bottomNavigationItems(context)

    val backStackEntry = navController.currentBackStackEntryAsState()
    val shouldShowBottomBar = backStackEntry.value?.destination?.route in items.map { it.route }

    if (shouldShowBottomBar) {
        NavigationBar(
            modifier = modifier.clip(
                RoundedCornerShape(
                    topStart = spacing.spaceMedium,
                    topEnd = spacing.spaceMedium
                )
            ),
        ) {
            items.forEach { item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                    label = { Text(text = item.label) })
            }
        }
    }
}