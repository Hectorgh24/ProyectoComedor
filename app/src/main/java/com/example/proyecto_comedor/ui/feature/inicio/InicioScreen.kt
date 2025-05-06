package com.example.proyecto_comedor.ui.feature.inicio

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyecto_comedor.ui.components.MenuTabs
import androidx.navigation.NavHostController

@Composable
fun InicioScreen(navController: NavHostController) {
    Spacer(modifier = Modifier.height(8.dp))
    MenuTabs(
        onCategoriaClick = { nombreCategoria ->
            navController.navigate("categoria/$nombreCategoria")
        }
    )
}





