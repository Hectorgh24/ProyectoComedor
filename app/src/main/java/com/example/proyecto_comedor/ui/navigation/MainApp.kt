/*package com.example.proyecto_comedor

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_comedor.ui.components.BottomNavigation
import com.example.proyecto_comedor.ui.feature.beca.BecaScreen
import com.example.proyecto_comedor.ui.feature.inicio.InicioScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "inicio_screen",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("inicio_screen") {
                InicioScreen()
            }
            composable("beca_screen") {
                BecaScreen()
            }
            // Add other screens as needed
        }
    }
}*/

package com.example.proyecto_comedor

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_comedor.ui.components.BottomNavigation
import com.example.proyecto_comedor.ui.feature.beca.BecaScreen
import com.example.proyecto_comedor.ui.feature.inicio.InicioScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "inicio_screen",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("inicio_screen") {
                InicioScreen()
            }
            composable("beca_screen") {
                BecaScreen()
            }
            // Add other screens as needed
        }
    }
}