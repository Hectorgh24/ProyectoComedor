package com.example.proyecto_comedor.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_comedor.R
import com.example.proyecto_comedor.data.model.FoodItem

@Composable
fun SegmentedButton(
    onCommentClick: () -> Unit,
    onInfoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedIndex by remember { mutableStateOf(0) }
    val options = listOf("Desayuno", "Comida")
    val desayunoItem = FoodItem(
        name = "Huevo con salsa roja",
        imageRes = R.drawable.huevo_salsa_roja,
        rating = 4.5f,
        reviewCount = 212,
        accompaniments = listOf(
            "Frijoles",
            "Ensalada de lechuga y pepino",
            "Agua de sandía"
        )
    )

    val comidaItem = FoodItem(
        name = "Milanesa de pollo",
        imageRes = R.drawable.milanesa,
        rating = 4.8f,
        reviewCount = 185,
        accompaniments = listOf(
            "Arroz",
            "Ensalada de verduras",
            "Agua de jamaica"
        )
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
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
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                        onClick = { selectedIndex = index },
                        selected = index == selectedIndex
                    ) {
                        Text(label)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            when (selectedIndex) {
                0 -> FoodItemCard(
                    foodItem = desayunoItem,
                    onCommentClick = onCommentClick,
                    onInfoClick = onInfoClick
                )
                1 -> FoodItemCard(
                    foodItem = comidaItem,
                    onCommentClick = onCommentClick,
                    onInfoClick = onInfoClick
                )
            }
        }
    }
}

@Composable
@Preview
fun SegmentedButtonPreview() {
    SegmentedButton(
        onCommentClick = {},
        onInfoClick = {}
    )
}
