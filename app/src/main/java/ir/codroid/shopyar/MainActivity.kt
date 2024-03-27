package ir.codroid.shopyar

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.codroid.shopyar.navigation.BottomNavigationBar
import ir.codroid.shopyar.navigation.Route
import ir.codroid.shopyar.ui.theme.ShopYarTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopYarTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show()
                            },
                            containerColor = MaterialTheme.colorScheme.primary
                        ) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "")
                        }
                    },
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    }
                ) {
                    NavHost(navController = navController, startDestination = Route.FACTOR ) {
                        composable(Route.FACTOR) {
                            Text(
                                text = "Factor",
                                style = TextStyle(fontSize = 60.sp),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        composable(Route.MERCHANDISE) {
                            Text(
                                text = "Merchandise",
                                style = TextStyle(fontSize = 60.sp),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        composable(Route.PROFILE) {
                            Text(
                                text = "Profile",
                                style = TextStyle(fontSize = 60.sp),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShopYarTheme {
        Greeting("Android")
    }
}