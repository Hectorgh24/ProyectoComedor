package com.example.proyecto_comedor.ui.components.Comentario

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SatisfaccionSeccion(
    onSatisfaccionSelected: (Int) -> Unit = {}
) {
    var selectedIndex by remember { mutableStateOf(-1) }

    // Sección de Satisfacción
    Text(
        text = "Satisfacción",
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
    )

    // Emoticones de satisfacción
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SatisfactionEmoji(
            icon = Icons.Outlined.SentimentVeryDissatisfied,
            label = "Muy malo",
            isSelected = selectedIndex == 0,
            onClick = {
                selectedIndex = 0
                onSatisfaccionSelected(1) // 1 para muy malo
            }
        )

        SatisfactionEmoji(
            icon = Icons.Outlined.SentimentDissatisfied,
            label = "Malo",
            isSelected = selectedIndex == 1,
            onClick = {
                selectedIndex = 1
                onSatisfaccionSelected(2) // 2 para malo
            }
        )

        SatisfactionEmoji(
            icon = Icons.Outlined.SentimentNeutral,
            label = "Regular",
            isSelected = selectedIndex == 2,
            onClick = {
                selectedIndex = 2
                onSatisfaccionSelected(3) // 3 para regular
            }
        )

        SatisfactionEmoji(
            icon = Icons.Outlined.SentimentSatisfied,
            label = "Bueno",
            isSelected = selectedIndex == 3,
            onClick = {
                selectedIndex = 3
                onSatisfaccionSelected(4) // 4 para bueno
            }
        )

        SatisfactionEmoji(
            icon = Icons.Outlined.SentimentVerySatisfied,
            label = "Muy bueno",
            isSelected = selectedIndex == 4,
            onClick = {
                selectedIndex = 4
                onSatisfaccionSelected(5) // 5 para muy bueno
            }
        )
    }
}

@Composable
fun SatisfactionEmoji(
    icon: ImageVector,
    label: String,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(32.dp),
            tint = if (isSelected) Color(0xFF4CAF50) else Color.Gray
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = if (isSelected) Color(0xFF4CAF50) else Color.Gray
        )
    }
}