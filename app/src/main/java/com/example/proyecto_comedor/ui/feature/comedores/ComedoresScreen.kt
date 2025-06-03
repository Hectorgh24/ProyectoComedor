package com.example.proyecto_comedor.ui.feature.comedores

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.MaterialTheme
import android.content.Intent
import android.net.Uri
import androidx.compose.material.icons.filled.Check
import com.example.proyecto_comedor.data.datasource.comedores
import com.example.proyecto_comedor.ui.components.ComedorCard
import com.example.proyecto_comedor.ui.components.HorarioCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComedoresScreen(onBackClick: () -> Unit = {}) {
    val context = LocalContext.current
    val comedoresPorRegion = comedores.groupBy { it.region }
    val todasLasRegiones = comedoresPorRegion.keys.toList().sorted()

    // Estado para las regiones seleccionadas
    var regionesSeleccionadas by remember {
        mutableStateOf(todasLasRegiones.toSet())
    }

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

        // Chips de filtro por región
        item {
            Text(
                text = "Filtrar por región:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 4.dp)
            ) {
                items(todasLasRegiones) { region ->
                    FilterChip(
                        onClick = {
                            regionesSeleccionadas = if (regionesSeleccionadas.contains(region)) {
                                regionesSeleccionadas - region
                            } else {
                                regionesSeleccionadas + region
                            }
                        },
                        label = { Text("Región $region") },
                        selected = regionesSeleccionadas.contains(region),
                        leadingIcon = if (regionesSeleccionadas.contains(region)) {
                            {
                                Icon(
                                    imageVector = androidx.compose.material.icons.Icons.Default.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        } else null
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Mostrar comedores de las regiones seleccionadas
        comedoresPorRegion.forEach { (region, comedoresDeRegion) ->
            if (regionesSeleccionadas.contains(region)) {
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

        // Mensaje cuando no hay regiones seleccionadas
        if (regionesSeleccionadas.isEmpty()) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    Text(
                        text = "Selecciona al menos una región para ver los comedores",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}