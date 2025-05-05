package com.example.proyecto_comedor.ui.feature.inicio

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.proyecto_comedor.ui.components.CategoriasGrid

@Composable
fun CartaScreen(onCategoriaClick: (String) -> Unit) {
    CategoriasGrid(onCategoriaClick = onCategoriaClick)
}
