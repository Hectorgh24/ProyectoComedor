/*package com.example.proyecto_comedor.ui.feature.beca

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyecto_comedor.ui.components.FoodSearchBar
import com.example.proyecto_comedor.ui.components.MenuTabs

@Composable

fun BecaScreen() {
    // Implementación de la pantalla de beca
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        FoodSearchBar()

        Spacer(modifier = Modifier.height(8.dp))

        MenuTabs()

    }
}*/

// BecaScreen.kt
package com.example.proyecto_comedor.ui.feature.beca

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyecto_comedor.ui.components.AppNavigationDrawer
import com.example.proyecto_comedor.ui.components.FoodSearchBar
import com.example.proyecto_comedor.ui.components.MenuTabs
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BecaScreen() {
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
        ) {
            FoodSearchBar(drawerState = drawerState)

            Spacer(modifier = Modifier.height(8.dp))


        }
    }
}