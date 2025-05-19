package com.example.proyecto_comedor.ui.components


import FoodItemCard
import com.example.proyecto_comedor.data.model.FoodItem
import androidx.compose.foundation.layout.*
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton as MaterialSegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SegmentedButton(
    desayunoItem: FoodItem,
    comidaItem:   FoodItem,
    onCommentClick: () -> Unit,
    onInfoClick:    (Int) -> Unit,  // ahora recibe el índice seleccionado
    modifier:       Modifier = Modifier
) {
    var selectedIndex by remember { mutableStateOf(0) }
    val options = listOf("Desayuno", "Comida")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // Row de botones segmentados
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                options.forEachIndexed { index, label ->
                    MaterialSegmentedButton(
                        shape    = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                        onClick  = { selectedIndex = index },
                        selected = index == selectedIndex
                    ) {
                        Text(text = label)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Card del platillo correspondiente
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            val item = if (selectedIndex == 0) desayunoItem else comidaItem
            FoodItemCard(
                foodItem       = item,
                onCommentClick = onCommentClick,
                // pasamos el índice para que el caller sepa si es desayuno (0) o comida (1)
                onInfoClick    = { onInfoClick(selectedIndex) }
            )
        }
    }
}