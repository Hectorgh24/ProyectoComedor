package com.example.proyecto_comedor.ui.feature.inicio

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_comedor.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.ui.draw.clip
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search



// Datos de ejemplo
data class ItemComida(
    val nombre: String,
    val descripcion: String,
    val imagenRes: Int
)

// Pantalla de lista dependiendo la categoría
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(categoria: String, onBack: () -> Unit) {
    val items = when (categoria) {
        "Antojitos" -> listOf(
            ItemComida("Enchiladas", "Tradicionales con queso", R.drawable.enchiladas),
            ItemComida("Chilaquiles", "Verdes o rojos con pollo", R.drawable.chilaquiles),
            ItemComida("Enfrijoladas", "Cubiertas en salsa de frijol", R.drawable.enfrijoladas)
        )
        "Guarniciones" -> listOf(
            ItemComida("Arroz rojo", "Acompañamiento clásico", R.drawable.arroz)
        )
        else -> listOf()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = categoria) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Acción de búsqueda */ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Buscar"
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 8.dp)
        ) {
            items(items.size) { index ->
                ItemCard(item = items[index])
            }
        }
    }
}


// Tarjeta de cada comida
@Composable
fun ItemCard(item: ItemComida) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { }
    ) {
        Image(
            painter = painterResource(id = item.imagenRes),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.nombre,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = item.descripcion,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
    Divider(thickness = 0.5.dp, color = Color.Gray)
}
