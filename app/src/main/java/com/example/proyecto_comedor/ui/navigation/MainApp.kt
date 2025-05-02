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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_comedor.ui.components.AppNavigationDrawer
import com.example.proyecto_comedor.ui.components.BottomNavigation
import com.example.proyecto_comedor.ui.components.FoodSearchBar
import com.example.proyecto_comedor.ui.feature.beca.BecaScreen
import com.example.proyecto_comedor.ui.feature.inicio.InicioScreen
import kotlinx.coroutines.launch

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    AppNavigationDrawer(
        drawerState = drawerState,
        onItemSelected = { route ->
            scope.launch {
                drawerState.close()
                navController.navigate(route) // Agrega navegación si se necesita
            }
        }
    ) {
        AppScaffold(
            navController = navController,
            drawerState = drawerState
        )
    }
}
@Composable
fun AppScaffold(
    navController: NavHostController,
    drawerState: DrawerState
) {
    Scaffold(
        topBar = {
            FoodSearchBar(drawerState = drawerState)
        },
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) { paddingValues ->
        NavigationHost(
            navController = navController,
            paddingValues = paddingValues
        )
    }
}
@Composable
fun NavigationHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
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
        // Agrega más pantallas según sea necesario
    }
}


/*@Composable
fun NavigationHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
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
        // Agrega más pantallas según sea necesario
    }
}*/



/*val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    AppNavigationDrawer(
        drawerState = drawerState,
        currentRoute = "inicio",
        onItemSelected = { route ->
            scope.launch {
                drawerState.close()
            }
        }
    ) {
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