package com.example.proyecto_comedor

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_comedor.ui.components.BottomNavigation
import com.example.proyecto_comedor.ui.components.FoodSearchBar
import com.example.proyecto_comedor.ui.navigation.AppNavGraph

@Composable
fun AppNavigation() {
    val navController = rememberNavController()


    AppScaffold(
        navController = navController,

        )
}

@Composable
fun AppScaffold(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val fullScreenRoutes = listOf("usuario", "configuracion", "acerca")
    val isFullScreenRoute = currentRoute in fullScreenRoutes || currentRoute?.startsWith("categoria/") == true

    if (isFullScreenRoute) {
        AppNavGraph(navController = navController, paddingValues = PaddingValues())
    } else {
        Scaffold(
            topBar = {
                FoodSearchBar(navController = navController)
            },
            bottomBar = {
                BottomNavigation(navController = navController)
            }
        ) { paddingValues ->
            AppNavGraph(
                navController = navController,
                paddingValues = paddingValues
            )
        }
    }
}

