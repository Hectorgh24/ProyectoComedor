package com.example.proyecto_comedor.ui.feature.inicio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyecto_comedor.ui.components.SegmentedButton
import com.example.proyecto_comedor.ui.components.InformacionNutricional
import com.example.proyecto_comedor.ui.components.Comentario
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDelDiaScreen() {
    val informacionSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val comentarioSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),

    ) {
        item {
            SegmentedButton(
                onCommentClick = {
                    coroutineScope.launch {
                        comentarioSheetState.show()
                    }
                },
                onInfoClick = {
                    coroutineScope.launch {
                        informacionSheetState.show()
                    }
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

    }

    // BottomSheet para Información
    InformacionNutricional(
        sheetState = informacionSheetState,
        onDismissRequest = {
            coroutineScope.launch {
                informacionSheetState.hide()
            }
        }
    )

    // BottomSheet para Comentario
    Comentario(
        sheetState = comentarioSheetState,
        onDismissRequest = {
            coroutineScope.launch {
                comentarioSheetState.hide()
            }
        }
    )
}
