package com.example.proyecto_comedor.ui.feature.inicio

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape


// Pantalla principal con categorías
@Composable
fun CartaScreen() {
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    if (selectedCategory != null) {
        ListScreen(categoria = selectedCategory!!) {
            selectedCategory = null // Acción para regresar
        }
    } else {
        CategoriasGrid(onCategoriaClick = { categoria ->
            selectedCategory = categoria
        })
    }
}

// Cuadrícula de botones de categorías
@Composable
fun CategoriasGrid(onCategoriaClick: (String) -> Unit) {
    val categorias = listOf(
        "Antojitos", "Guarniciones",
        "Sandwiches", "Tortas",
        "Postres", "Otros"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Categorías",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categorias.size) { index ->
                CategoriaButton(nombre = categorias[index]) {
                    onCategoriaClick(categorias[index])
                }
            }
        }
    }
}

// Botón individual para categoría
@Composable
fun CategoriaButton(nombre: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFEAF1EA),
            contentColor = Color.Black
        )
    ) {
        Text(
            text = nombre,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
