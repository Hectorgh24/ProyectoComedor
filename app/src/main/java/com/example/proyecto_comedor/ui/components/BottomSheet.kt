package com.example.proyecto_comedor.ui.components

import EvaluacionSeccion
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyecto_comedor.ui.screen.InformacionNutrimentalUiState
import com.example.proyecto_comedor.ui.screen.InformacionNutrimentalViewModel
import com.example.proyecto_comedor.ui.screen.InformacionNutrimentalViewModel.Companion.Factory
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
    if (!sheetState.isVisible) return

    // Obtain the ViewModel
    val viewModel: InformacionNutrimentalViewModel = viewModel(factory = Factory)
    val uiState: InformacionNutrimentalUiState by remember { derivedStateOf { viewModel.uiState } }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        Column(Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
            Text(
                text = "Información Nutrimental",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            when {
                uiState.isLoading -> {
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                uiState.isError -> {
                    Text(
                        text = "Error al cargar la información nutrimental",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                else -> {
                    // Desayuno section
                    uiState.desayuno?.let { info ->
                        Text("Desayuno", style = MaterialTheme.typography.titleMedium)
                        InformacionNutrimental(
                            nombrePlatillo    = "Desayuno",
                            kilocalorias      = info.kcal.toString(),
                            hidratosCarbono   = info.hc.toString(),
                            proteinas         = info.p.toString(),
                            lipidos           = info.l.toString()
                        )
                        Spacer(Modifier.height(24.dp))
                    }
                    // Comida section
                    uiState.comida?.let { info ->
                        Text("Comida", style = MaterialTheme.typography.titleMedium)
                        InformacionNutrimental(
                            nombrePlatillo    = "Comida",
                            kilocalorias      = info.kcal.toString(),
                            hidratosCarbono   = info.hc.toString(),
                            proteinas         = info.p.toString(),
                            lipidos           = info.l.toString()
                        )
                        Spacer(Modifier.height(24.dp))
                    }
                }
            }
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
            onDismissRequest = onDismissRequest,
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Comentario",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                BotonesSeccion()
                EvaluacionSeccion()
                SatisfaccionSeccion()
                Spacer(modifier = Modifier.height(25.dp))
                BotonEnviar()
                Spacer(modifier = Modifier.height(25.dp))
            }
        }
    }
}
