package com.example.proyecto_comedor.ui.components.Informacion

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InformacionNutrimental(
    nombrePlatillo: String,
    porcion: String,
    kilocalorias: String,
    hidratosCarbono: String,
    proteinas: String,
    lipidos: String,
    modifier: Modifier = Modifier
) {

        Column(
            modifier = Modifier
                //.fillMaxWidth()
                .padding(16.dp)
        ) {
            // Incluir el componente de DatoCurioso aquí mediante composición
            DatoCurioso("Este platillo te aporta.......")

            // Nombre del platillo y porción
            Text(
                text = nombrePlatillo,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )

            // Información nutricional en formato de tarjetas
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                NutrienteCard(
                    valor = kilocalorias,
                    etiqueta = "KCAL",
                    descripcion = "Kilocalorías",
                    color = Color(0xFFFFE0B2),
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                NutrienteCard(
                    valor = hidratosCarbono,
                    etiqueta = "HC",
                    descripcion = "Hidratos de Carbono (g)",
                    color = Color(0xFFE1BEE7),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                NutrienteCard(
                    valor = proteinas,
                    etiqueta = "P",
                    descripcion = "Proteínas (g)",
                    color = Color(0xFFBBDEFB),
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                NutrienteCard(
                    valor = lipidos,
                    etiqueta = "L",
                    descripcion = "Lípidos (g)",
                    color = Color(0xFFFFCCBC),
                    modifier = Modifier.weight(1f)
                )
            }
        }

}

