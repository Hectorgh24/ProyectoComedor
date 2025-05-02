package com.example.proyecto_comedor.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class FoodItem(
    val name: String,
    val imageRes: Int,
    val rating: Float,
    val reviewCount: Int,
    val accompaniments: List<String>
)

@Composable
fun FoodItemCard(
    foodItem: FoodItem,
    onCommentClick: () -> Unit,
    onInfoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
       // colors = CardDefaults.cardColors(containerColor = Color(0xFFe7edde))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = foodItem.imageRes),
                contentDescription = foodItem.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = foodItem.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Row {
                RatingBar(rating = foodItem.rating)
                Text(
                    text = " ${foodItem.rating} (${foodItem.reviewCount})",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Acompañado de:",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )

            Column(modifier = Modifier.padding(start = 8.dp, top = 4.dp)) {
                foodItem.accompaniments.forEach { item ->
                    Text(
                        text = "• $item",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    onClick = onCommentClick,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(20.dp),
                    //border = BorderStroke(1.dp, Color(0xFF79747e))
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Text("Comentario")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = onInfoClick,
                    modifier = Modifier.weight(1f),
                    //colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF557f40)),
                    //shape = RoundedCornerShape(20.dp)
                ) {
                    Text("Información")
                }
            }
        }
    }
}

@Composable
fun RatingBar(rating: Float, maxStars: Int = 5) {
    Row {
        repeat(maxStars) { index ->
            val starFilled = index < rating
            Text(
                text = "★",
                color = if (starFilled) Color(0xFFFFB800) else Color(0xFFDDDDDD),
                fontSize = 18.sp
            )
        }
    }
}
