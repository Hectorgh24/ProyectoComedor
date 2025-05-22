package com.example.proyecto_comedor.ui.components

import EvaluacionSeccion
import com.example.proyecto_comedor.ui.components.Comentario.BotonEnviar
import com.example.proyecto_comedor.ui.components.Comentario.BotonesSeccion
import com.example.proyecto_comedor.ui.components.Comentario.SatisfaccionSeccion
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyecto_comedor.MenuDelDiaApplication
import com.example.proyecto_comedor.ui.components.Informacion.InformacionNutrimental
import com.example.proyecto_comedor.ui.screen.ComentarioViewModel
import com.example.proyecto_comedor.ui.screen.InformacionNutrimentalUiState
import com.example.proyecto_comedor.ui.screen.InformacionNutrimentalViewModel
import com.example.proyecto_comedor.ui.screen.InformacionNutrimentalViewModel.Companion.Factory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformacionNutricional(
    sheetState: SheetState,
    selectedIndex: Int,             // <-- aquí el índice
    onDismissRequest: () -> Unit
) {
    val viewModel: InformacionNutrimentalViewModel = viewModel(factory = Factory)
    val uiState: InformacionNutrimentalUiState by remember { derivedStateOf { viewModel.uiState } }

    if (!sheetState.isVisible) return

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState       = sheetState
    ) {
        Column(
            Modifier
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
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        contentAlignment = Alignment.Center
                    ) {
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
                    // <-- mostramos solo desayuno o comida, según el índice
                    if (selectedIndex == 0) {
                        uiState.desayuno?.let { info ->
                            Text("Desayuno", style = MaterialTheme.typography.titleMedium)
                            InformacionNutrimental(
                                nombrePlatillo    = "Desayuno",
                                kilocalorias      = info.kcal.toString(),
                                hidratosCarbono   = info.hc.toString(),
                                proteinas         = info.p.toString(),
                                lipidos           = info.l.toString()
                            )
                        }
                    } else {
                        uiState.comida?.let { info ->
                            Text("Comida", style = MaterialTheme.typography.titleMedium)
                            InformacionNutrimental(
                                nombrePlatillo    = "Comida",
                                kilocalorias      = info.kcal.toString(),
                                hidratosCarbono   = info.hc.toString(),
                                proteinas         = info.p.toString(),
                                lipidos           = info.l.toString()
                            )
                        }
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
    menuId: Int, // ID del menú del día (desayuno o comida)
    onDismissRequest: () -> Unit
) {
    if (!sheetState.isVisible) return
    
    val app = LocalContext.current.applicationContext as MenuDelDiaApplication
    val viewModel: ComentarioViewModel = viewModel(
        factory = ComentarioViewModel.provideFactory(app.container4.comentarioRepository)
    )
    
    val uiState by remember { derivedStateOf { viewModel.uiState } }
    
    // Actualizar el ID cuando se abre el sheet
    LaunchedEffect(menuId) {
        viewModel.actualizarIdDesayunoComida(menuId)
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState       = sheetState
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
            
            // Ignoramos los botones QR como solicitaste
            BotonesSeccion()
            
            EvaluacionSeccion(
                onPrecioSelected = { viewModel.actualizarPrecio(it) },
                onPorcionSelected = { viewModel.actualizarPorcion(it) },
                onSaborSelected = { viewModel.actualizarSabor(it) }
            )
            
            SatisfaccionSeccion(
                onSatisfaccionSelected = { viewModel.actualizarSatisfaccionGeneral(it) }
            )
            
            Spacer(modifier = Modifier.height(25.dp))
            
            // Mostrar estado de carga o error
            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                uiState.isError -> {
                    Text(
                        text = "Error: ${uiState.errorMessage}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                uiState.isSuccess -> {
                    Text(
                        text = "¡Comentario enviado exitosamente!",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
            
            BotonEnviar(
                onEnviarClick = { 
                    viewModel.enviarComentario()
                }
            )
            
            Spacer(modifier = Modifier.height(25.dp))
        }
    }
}