package com.example.proyecto_comedor

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_comedor.ui.components.BottomNavigation
import com.example.proyecto_comedor.ui.feature.beca.BecaScreen
import com.example.proyecto_comedor.ui.feature.inicio.InicioScreen
import com.example.proyecto_comedor.ui.feature.login.LoginScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != "login_screen") {
                BottomNavigation(navController = navController)
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = "login_screen"
        ) {
            composable("login_screen") {
                LoginScreen(navController = navController)
            }
            composable("inicio_screen") {
                InicioScreen()
            }
            composable("beca_screen") {
                BecaScreen()
            }
        }
    }
}