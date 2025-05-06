package com.example.proyecto_comedor.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_comedor.data.model.Comedor

@Composable
fun ComedorCard(comedor: Comedor, onMapClick: () -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(comedor.nombre, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            Spacer(modifier = Modifier.height(4.dp))
            Text(comedor.ubicacion, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Navegación
                Button(
                    onClick = {
                        val destination = comedor.coordenadas ?: "${comedor.nombre}, ${comedor.ubicacion}"
                        val navigationUri = if (comedor.coordenadas != null)
                            "google.navigation:q=${comedor.coordenadas}"
                        else
                            "google.navigation:q=${Uri.encode(destination)}"

                        val navIntent = Intent(Intent.ACTION_VIEW, Uri.parse(navigationUri)).apply {
                            setPackage("com.google.android.apps.maps")
                        }

                        val webIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.google.com/maps/dir/?api=1&destination=${Uri.encode(destination)}&travelmode=driving")
                        )

                        try {
                            if (navIntent.resolveActivity(context.packageManager) != null) {
                                context.startActivity(navIntent)
                            } else {
                                context.startActivity(webIntent)
                            }
                        } catch (e: Exception) {
                            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(comedor.mapsUrl)))
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                ) {
                    Icon(Icons.Default.Directions, "Cómo llegar", Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Cómo llegar")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = onMapClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Icon(Icons.Default.LocationOn, "Ver en Maps", Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Ver en Maps")
                }
            }
        }
    }
}
