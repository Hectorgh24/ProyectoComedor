package com.example.proyecto_comedor.ui.feature.inicio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyecto_comedor.R
import com.example.proyecto_comedor.ui.components.FoodItem
import com.example.proyecto_comedor.ui.components.FoodItemCard
import com.example.proyecto_comedor.ui.components.SegmentedButton
import com.example.proyecto_comedor.ui.components.InformacionNutricional
import com.example.proyecto_comedor.ui.components.Comentario
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDelDiaScreen() {
    val foodItems = listOf(
        FoodItem(
            name = "Huevo con salsa roja",
            imageRes = R.drawable.huevo_salsa_roja,
            rating = 4.5f,
            reviewCount = 212,
            accompaniments = listOf(
                "Frijoles",
                "Ensalada de lechuga y pepino",
                "Agua de sandía"
            )
        ),
        // Puedes agregar más alimentos aquí
    )

    val informacionSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val comentarioSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    Spacer(modifier = Modifier.height(16.dp))

    SegmentedButton()

    Spacer(modifier = Modifier.height(16.dp))

    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(foodItems) { foodItem ->
            FoodItemCard(
                foodItem = foodItem,
                onCommentClick = {
                    coroutineScope.launch {
                        comentarioSheetState.show()
                    }
                },
                onInfoClick = {
                    coroutineScope.launch {
                        informacionSheetState.show()
                    }
                }
            )
        }
    }

    // BottomSheet para Información
    InformacionNutricional(
        sheetState = informacionSheetState,
        onDismissRequest = {
            coroutineScope.launch {
                informacionSheetState.hide()
            }
        }
    )

    // BottomSheet para Comentario
    Comentario(
        sheetState = comentarioSheetState,
        onDismissRequest = {
            coroutineScope.launch {
                comentarioSheetState.hide()
            }
        }
    )
}
