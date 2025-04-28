package com.example.proyecto_comedor.ui.components

import EvaluacionSeccion
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import com.example.proyecto_comedor.ui.components.Comentario.BotonEnviar
import com.example.proyecto_comedor.ui.components.Comentario.BotonesSeccion
import com.example.proyecto_comedor.ui.components.Comentario.SatisfaccionSeccion
import com.example.proyecto_comedor.ui.components.Informacion.InformacionNutrimental

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformacionNutricional(
    sheetState: SheetState,
    onDismissRequest: () -> Unit
) {
    if (sheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = { onDismissRequest() },
            sheetState = sheetState
        ) {
            // Aquí va el contenido de tu BottomSheet (vacío por ahora)
            Text(
                text = "Información nutrimental",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )

            InformacionNutrimental(
                nombrePlatillo = "Chileatole de Verduras (1 taza / 240 ml)",
                porcion = "1 taza / 240 ml",
                kilocalorias = "160",
                hidratosCarbono = "30",
                proteinas = "4",
                lipidos = "4"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Comentario(
    sheetState: SheetState,
    onDismissRequest: () -> Unit
) {
    if (sheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = { onDismissRequest() },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                // Título principal
                Text(
                    text = "Comentario",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Sección de botones
                BotonesSeccion()

                // Sección de evaluación
                EvaluacionSeccion()

                // Sección de satisfacción
                SatisfaccionSeccion()

                // Botón Enviar
                BotonEnviar()

                // Espaciador para dar padding al final
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}



