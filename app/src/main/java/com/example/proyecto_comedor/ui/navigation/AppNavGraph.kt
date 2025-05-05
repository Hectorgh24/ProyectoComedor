package com.example.proyecto_comedor.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.proyecto_comedor.ui.components.CategoriaDetalleScreen
import com.example.proyecto_comedor.ui.feature.dropdown.AcercaScreen
import com.example.proyecto_comedor.ui.feature.beca.BecaScreen
import com.example.proyecto_comedor.ui.feature.comedores.ComedoresScreen
import com.example.proyecto_comedor.ui.feature.dropdown.ConfiguracionScreen
import com.example.proyecto_comedor.ui.feature.inicio.InicioScreen
import com.example.proyecto_comedor.ui.feature.dropdown.UsuarioScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = "inicio_screen",
        modifier = Modifier.padding(paddingValues)
    ) {
        composable("inicio_screen") {
            InicioScreen(navController = navController)
        }
        composable("comedores-screen") {
            ComedoresScreen()
        }
        composable("beca_screen") {
            BecaScreen()
        }
        // Aquí puedes agregar las pantallas que usarás desde el DropdownMenu
        composable("usuario") {
            UsuarioScreen(navController = navController)
        }
        composable("configuracion") {
            ConfiguracionScreen(navController = navController)
        }
        composable("acerca") {
            AcercaScreen(navController = navController)
        }

        // Una única ruta parameterizada para todas las categorías
        composable(
            route = "categoria/{nombreCategoria}",
            arguments = listOf(navArgument("nombreCategoria") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombreCategoria = backStackEntry.arguments?.getString("nombreCategoria") ?: "Desconocida"
            CategoriaDetalleScreen(nombreCategoria = nombreCategoria, navController = navController)
        }
    }
}
