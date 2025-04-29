package com.example.proyecto_comedor.ui.feature.inicio

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyecto_comedor.R

@Composable
fun PromocionesScreen() {
    // Implementación de la pantalla de carta
    Spacer(modifier = Modifier.height(90.dp))

    Image(
        painter = painterResource(id = R.drawable.promo), // reemplaza por tu imagen
        contentDescription = "Imagen informativa de promociones",
        modifier = Modifier
            .height(450.dp)
            .fillMaxSize()
    )
}