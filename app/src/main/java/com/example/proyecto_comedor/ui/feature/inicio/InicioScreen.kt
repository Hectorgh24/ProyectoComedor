// InicioScreen.kt
/*package com.example.proyecto_comedor.ui.feature.inicio

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyecto_comedor.ui.components.FoodSearchBar
import com.example.proyecto_comedor.ui.components.MenuTabs

@Composable
fun InicioScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        FoodSearchBar()

        Spacer(modifier = Modifier.height(8.dp))

        MenuTabs()

    }
}*/

package com.example.proyecto_comedor.ui.feature.inicio


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyecto_comedor.ui.components.AppNavigationDrawer
import com.example.proyecto_comedor.ui.components.FoodSearchBar
import com.example.proyecto_comedor.ui.components.MenuTabs
import kotlinx.coroutines.launch

@Composable
fun InicioScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    AppNavigationDrawer(
        drawerState = drawerState,
        currentRoute = "inicio",
        onItemSelected = { route ->
            // Aquí puedes manejar la navegación a diferentes rutas
            // Por ejemplo, usando un NavController
            scope.launch {
                drawerState.close()
            }
        }
    ) {
        // Contenido principal
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(top = 36.dp)//solución al error de espaciado superior
        ) {

            FoodSearchBar(drawerState = drawerState)

            Spacer(modifier = Modifier.height(8.dp))

            MenuTabs()
        }
    }
}