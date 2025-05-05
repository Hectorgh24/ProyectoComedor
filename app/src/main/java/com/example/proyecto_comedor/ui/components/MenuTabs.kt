// MenuTabs.kt
package com.example.proyecto_comedor.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.proyecto_comedor.ui.feature.inicio.MenuDelDiaScreen
import com.example.proyecto_comedor.ui.feature.inicio.PromocionesScreen
import com.example.proyecto_comedor.ui.feature.inicio.CartaScreen

@Composable
fun MenuTabs(onCategoriaClick: (String) -> Unit) {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("Menú del día", "Carta", "Promociones")
    Column {
        PrimaryTabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                )
            }
        }
        // Contenido que cambia según la pestaña seleccionada
        when (state) {
            0 -> MenuDelDiaScreen()
            1 -> CartaScreen(onCategoriaClick = onCategoriaClick)
            2 -> PromocionesScreen()
        }
    }
}


