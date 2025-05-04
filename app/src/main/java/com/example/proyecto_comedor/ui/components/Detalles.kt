package com.example.proyecto_comedor.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetalleCategoriaScreen(nombre: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Detalle de categoría: $nombre", style = MaterialTheme.typography.headlineMedium)
        // Aquí puedes agregar más contenido según la categoría
    }
}
