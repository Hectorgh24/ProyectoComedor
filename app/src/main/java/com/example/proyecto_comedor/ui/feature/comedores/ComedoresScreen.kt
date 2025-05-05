package com.example.proyecto_comedor.ui.feature.comedores

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.DoorFront
import androidx.compose.material.icons.filled.FreeBreakfast
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.outlined.DoorFront
import androidx.compose.material.icons.outlined.FreeBreakfast
import androidx.compose.material.icons.outlined.LunchDining
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

data class Comedor(
    val nombre: String,
    val ubicacion: String,
    val region: String,
    val mapsUrl: String,
    val coordenadas: String? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComedoresScreen(onBackClick: () -> Unit = {}) {
    val comedores = listOf(
        // Región Xalapa
        Comedor(
            nombre = "Comedor Universitario de la Unidad de Humanidades",
            ubicacion = "Francisco Moreno, Esq. Ezequiel Alatriste, Colonia Ferrer Guardia. C. P. 91020",
            region = "Xalapa",
            mapsUrl = "https://maps.app.goo.gl/KFsYh4PqFbEt555u5",
            coordenadas = "19.538810694278773, -96.93428396148407"
        ),
        Comedor(
            nombre = "Comedor Universitario de la Unidad de Ingeniería y Ciencias Químicas",
            ubicacion = "Universitario Gonzalo Aguirre Beltrán, Zona Universitaria, 91090",
            region = "Xalapa",
            mapsUrl = "https://maps.app.goo.gl/oPAcMKpU2jZwZdEv6",
            coordenadas = "19.518116473676166, -96.91684207609825"
        ),
        Comedor(
            nombre = "Comedor Universitario \"Ajuiyak Tlakualli\"",
            ubicacion = "Circuito Gonzalo Aguirre Beltrán S/N, Zona Universitaria, 91090",
            region = "Xalapa",
            mapsUrl = "https://maps.app.goo.gl/2NuCFNeKv41Nsbo6A",
            coordenadas = "19.51590993380116, -96.91851320981988"
        ),
        Comedor(
            nombre = "Comedor Universitario de la Unidad de Artes",
            ubicacion = "Belisario Domínguez, Zona Centro, Centro, 91000",
            region = "Xalapa",
            mapsUrl = "https://maps.app.goo.gl/7m3USk2RidmBZ4TK7",
            coordenadas = "19.524740617692594, -96.92413489432671"
        ),
        Comedor(
            nombre = "Comedor Universitario \"Komalli\" de la Facultad de Geografía",
            ubicacion = "Avenida Xalapa s/n, Obrero Campesina, 91020",
            region = "Xalapa",
            mapsUrl = "https://maps.app.goo.gl/aByvGPKZTaS8kerRA",
            coordenadas = "19.54177634718306, -96.92759249541938"
        ),

        // Región Poza Rica-Tuxpan
        Comedor(
            nombre = "Comedor Universitario \"Rincón de los Universitarios\"",
            ubicacion = "Carretera Tuxpan Tampico Kilómetro 7.5, Universitaria 92870",
            region = "Poza Rica-Tuxpan",
            mapsUrl = "https://maps.app.goo.gl/RKdw6Z9sdZkmC1CU8",
            coordenadas = "20.95307520288929, -97.45702977675762"

        ),
        Comedor(
            nombre = "Comedor Universitario \"Kgalhstúm\" de la Unidad de Ingenierías",
            ubicacion = "Prolongación Av. Venustiano Carranza s/n, Revolución, 93390",
            region = "Poza Rica-Tuxpan",
            mapsUrl = "https://maps.app.goo.gl/ZKFCZ2LpvR94U6LB8",
            coordenadas = "20.50941429152344, -97.44666710164687"
        )
    )

    val comedoresPorRegion = comedores.groupBy { it.region }
    val context = LocalContext.current


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()

                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                HorarioCard()
                Spacer(modifier = Modifier.height(16.dp))
            }

            comedoresPorRegion.forEach { (region, comedoresDeRegion) ->
                item {
                    Text(
                        text = "Región $region",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                items(comedoresDeRegion) { comedor ->
                    ComedorCard(
                        comedor = comedor,
                        onMapClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(comedor.mapsUrl))
                            context.startActivity(intent)
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

}

@Composable
fun HorarioCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.medium
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = "Horarios",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Horarios de atención",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            HorarioItem("Abierto", "8:00 am – 5:00 pm", Icons.Outlined.DoorFront)
            HorarioItem("Desayuno", "9:00 am – 12:00 pm o hasta agotar existencia", Icons.Outlined.FreeBreakfast)
            HorarioItem("Comida", "1:00 pm – 4:30 pm o hasta agotar existencia", Icons.Outlined.LunchDining)
            HorarioItem("Carta", "9:00 am – 4:30 pm", Icons.AutoMirrored.Outlined.MenuBook)
        }
    }
}

@Composable
fun HorarioItem(titulo: String, horario: String, icono: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icono,
            contentDescription = titulo,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = titulo,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = horario,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/*@Composable
fun ComedorCard(comedor: Comedor, onMapClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.medium
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = comedor.nombre,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = comedor.ubicacion,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onMapClick,
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Ver en Maps",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Ver en Maps")
            }
        }
    }
}*/

@Composable
fun ComedorCard(comedor: Comedor, onMapClick: () -> Unit) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.medium
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = comedor.nombre,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = comedor.ubicacion,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Botón para obtener indicaciones desde la ubicación actual
                Button(
                    onClick = {
                        // Determinar el destino para la navegación
                        val navigationUri = if (comedor.coordenadas != null) {
                            // Si tenemos coordenadas exactas, usarlas (más preciso)
                            "google.navigation:q=${comedor.coordenadas}"
                        } else {
                            // Si no hay coordenadas, usar el nombre y ubicación
                            "google.navigation:q=${Uri.encode("${comedor.nombre}, ${comedor.ubicacion}")}"
                        }

                        // Intent principal para navegación
                        val navigationIntent = Intent(Intent.ACTION_VIEW, Uri.parse(navigationUri))
                        navigationIntent.setPackage("com.google.android.apps.maps")

                        // Intent de respaldo usando la web
                        val destination = comedor.coordenadas ?: "${comedor.nombre}, ${comedor.ubicacion}"
                        val webIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.google.com/maps/dir/?api=1&destination=${Uri.encode(destination)}&travelmode=driving")
                        )

                        try {
                            // Intentar abrir con la app de Google Maps
                            if (navigationIntent.resolveActivity(context.packageManager) != null) {
                                context.startActivity(navigationIntent)
                            } else {
                                // Si no está instalada, usar la versión web
                                context.startActivity(webIntent)
                            }
                        } catch (e: Exception) {
                            // Si hay algún error, abrir la URL original
                            val fallbackIntent = Intent(Intent.ACTION_VIEW, Uri.parse(comedor.mapsUrl))
                            context.startActivity(fallbackIntent)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Directions,
                        contentDescription = "Cómo llegar",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Cómo llegar")
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Botón original para ver en Maps
                Button(
                    onClick = onMapClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Ver en Maps",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Ver en Maps")
                }
            }
        }
    }
}


// Vista previa
@Composable
@Preview(showBackground = true)
fun ComedoresScreenPreview() {
    MaterialTheme {
        ComedoresScreen()
    }
}