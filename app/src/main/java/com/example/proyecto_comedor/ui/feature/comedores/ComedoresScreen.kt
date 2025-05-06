package com.example.proyecto_comedor.ui.feature.comedores

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.MaterialTheme
import android.content.Intent
import android.net.Uri
import com.example.proyecto_comedor.data.datasource.comedores
import com.example.proyecto_comedor.ui.components.ComedorCard
import com.example.proyecto_comedor.ui.components.HorarioCard

@Composable
fun ComedoresScreen(onBackClick: () -> Unit = {}) {
    val context = LocalContext.current
    val comedoresPorRegion = comedores.groupBy { it.region }

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

