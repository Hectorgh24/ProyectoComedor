package com.example.proyecto_comedor.ui.feature.inicio

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CartaScreen() {
    // Aquí puedes agregar el contenido de la pantalla del menú del día
    // Por ejemplo, una lista de platos o un diseño específico para mostrar el menú
    CategoriasGrid()
}

@Composable
fun CategoriasGrid() {
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

        // Lista de categorías
        val categorias = listOf(
            "Antojitos", "Guarniciones",
            "Sandwiches", "Tortas",
            "Postres", "Otros",
            "Label", "Label"
        )

        // Creamos la cuadrícula con 2 columnas
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categorias.size) { index ->
                CategoriaButton(nombre = categorias[index])
            }
        }
    }
}

@Composable
fun CategoriaButton(nombre: String) {
    Button(
        onClick = { /* Acción al hacer clic en la categoría */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFEAF1EA),
            contentColor = Color.Black
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        )
    ) {
        Text(
            text = nombre,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

