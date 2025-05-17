package com.example.proyecto_comedor.ui.feature.inicio

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyecto_comedor.ui.components.SegmentedButton
import com.example.proyecto_comedor.ui.components.InformacionNutricional
import com.example.proyecto_comedor.ui.components.Comentario
import com.example.proyecto_comedor.ui.screen.MenuDelDiaUiState
import com.example.proyecto_comedor.ui.screen.MenuDelDiaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDelDiaScreen(
    viewModel: MenuDelDiaViewModel = viewModel(factory = MenuDelDiaViewModel.Factory)
) {
    // Directamente observa la propiedad mutableStateOf del ViewModel
    val uiState = viewModel.uiState

    val infoSheet = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val commentSheet = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            when {
                uiState.isLoading -> {
                    Text("Cargando menú...", modifier = Modifier.padding(16.dp))
                }
                uiState.isError -> {
                    Text("Error al cargar los menús.", modifier = Modifier.padding(16.dp))
                }
                uiState.desayuno != null && uiState.comida != null -> {
                    SegmentedButton(
                        desayunoItem = uiState.desayuno,
                        comidaItem = uiState.comida,
                        onCommentClick = { scope.launch { commentSheet.show() } },
                        onInfoClick    = { scope.launch { infoSheet.show() } },
                        modifier       = Modifier.padding(horizontal = 16.dp)
                    )
                }
                else -> {
                    Text("No hay datos de menú disponibles.", modifier = Modifier.padding(16.dp))
                }
            }
        }
    }

    InformacionNutricional(
        sheetState = infoSheet,
        onDismissRequest = { scope.launch { infoSheet.hide() } }
    )

    Comentario(
        sheetState = commentSheet,
        onDismissRequest = { scope.launch { commentSheet.hide() } }
    )
}
