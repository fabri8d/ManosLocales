package com.undef.ManosLocales.ui.screens.mainmenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.undef.ManosLocales.ui.screens.details.ProductDetailScreen
import com.undef.ManosLocales.ui.screens.details.SellerDetailScreen
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import com.undef.ManosLocales.ui.screens.BecomeSellerScreen
import com.undef.ManosLocales.ui.viewmodels.MainMenuViewModel
import com.undef.ManosLocales.ui.viewmodels.SellerViewModel
import com.undef.ManosLocales.ui.viewmodels.UserViewModel


@AndroidEntryPoint
class MainMenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MenuNavHost()
        }
    }
}

@Composable
fun MenuNavHost() {
    val navController = rememberNavController()
    val mainMenuViewModel: MainMenuViewModel = hiltViewModel()
    val userViewModel: UserViewModel = hiltViewModel()
    val sellerViewModel: SellerViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        userViewModel.loadSession()
    }
    NavHost(navController, startDestination = "MainMenu") {
        composable("MainMenu") {
            Posts(
                viewModel = mainMenuViewModel,
                onNavigateToProduct = { productId ->
                    navController.navigate("ProductView/$productId")
                },
                onNavigateToSeller = { sellerId ->
                    navController.navigate("SellerView/$sellerId")
                },
                onNavigateToFavorites = {
                    navController.navigate("Favorites")
                },
                onNavigateToSettings = {
                    navController.navigate("Settings")
                }
            )
        }

        composable("Settings") {
            SettingsScreen(
                onNavigateToMainMenu = {
                    navController.navigate("MainMenu")
                },
                onNavigateToFavorites = {
                    navController.navigate("Favorites")
                },
                onNavigateToModifyAccount = {
                    navController.navigate("ModifyAccount")
                },
                onNavigateToBecomeSeller = {
                    navController.navigate("BecomeSeller")
                }
            )
        }

        composable("ModifyAccount") {
            ModifyAccount(
                onNavigateToFavorites = {
                    navController.navigate("Favorites")
                },
                onNavigateToSettings = {
                    navController.navigate("Settings")
                },
                onNavigateToMainMenu = {
                    navController.navigate("MainMenu")
                }
            )
        }

        composable("Favorites") {
            FavoritesScreen(
                onNavigateToProduct = { productId -> navController.navigate("ProductView/$productId") },
                onNavigateToSettings = {
                    navController.navigate("Settings")
                },
                onNavigateToMainMenu = {
                    navController.navigate("MainMenu")
                }
            )
        }

        composable("ProductView/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            ProductDetailScreen(
                productId = productId,
                onNavigateToSeller = { sellerId -> navController.navigate("SellerView/$sellerId") },
                onNavigateToProduct = { pid -> navController.navigate("ProductView/$pid") },
                onNavigateToMainMenu = {
                    navController.navigate("MainMenu")
                },
                onNavigateToFavorites = {
                    navController.navigate("Favorites")
                },
                onNavigateToSettings = {
                    navController.navigate("Settings")
                }
            )
        }

        composable("SellerView/{sellerId}") { backStackEntry ->
            val sellerId = backStackEntry.arguments?.getString("sellerId")
            SellerDetailScreen(
                sellerId = sellerId,
                onNavigateToProduct = { pid -> navController.navigate("ProductView/$pid") },
                onNavigateToMainMenu = {
                    navController.navigate("MainMenu")
                },
                onNavigateToFavorites = {
                    navController.navigate("Favorites")
                },
                onNavigateToSettings = {
                    navController.navigate("Settings")
                }
            )
        }

        composable("BecomeSeller") {
            BecomeSellerScreen(
                sellerViewModel = sellerViewModel,
                userViewModel = userViewModel,
                onSellerCreated = {
                    // Acción al crear vendedor: por ejemplo, volver al MainMenu
                    navController.navigate("MainMenu") {
                        popUpTo("MainMenu") { inclusive = true }
                    }
                },
                onCancel = {
                    // Acción al cancelar: volver atrás
                    navController.popBackStack()
                }
            )
        }
    }
}
