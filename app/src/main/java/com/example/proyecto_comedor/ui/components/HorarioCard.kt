package com.example.proyecto_comedor.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.outlined.DoorFront
import androidx.compose.material.icons.outlined.FreeBreakfast
import androidx.compose.material.icons.outlined.LunchDining
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HorarioCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.AccessTime, "Horarios", tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Horarios de atención", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            }
            Spacer(Modifier.height(12.dp))
            HorarioItem("Abierto", "8:00 am – 5:00 pm", Icons.Outlined.DoorFront)
            HorarioItem("Desayuno", "9:00 am – 12:00 pm o hasta agotar existencia", Icons.Outlined.FreeBreakfast)
            HorarioItem("Comida", "1:00 pm – 4:30 pm o hasta agotar existencia", Icons.Outlined.LunchDining)
            HorarioItem("Carta", "9:00 am – 4:30 pm", Icons.AutoMirrored.Outlined.MenuBook)
        }
    }
}
