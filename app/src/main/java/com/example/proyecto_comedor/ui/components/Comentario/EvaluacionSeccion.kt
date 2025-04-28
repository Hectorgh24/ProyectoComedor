package com.example.proyecto_comedor.ui.components.Comentario

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun EvaluacionSeccion() {
    // Sección de Evaluación
    Text(
        text = "Evaluación",
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
    )

    Text(
        text = "Aspectos",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 4.dp)
    )

    // Dropdowns para evaluar aspectos
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Dropdown de Precio
        DropdownSelector(
            text = "Precio",
            modifier = Modifier.weight(1f)
        )

        // Dropdown de Cantidad
        DropdownSelector(
            text = "Cantidad",
            modifier = Modifier.weight(1f)
        )

        // Dropdown de Sabor
        DropdownSelector(
            text = "Sabor",
            modifier = Modifier.weight(1f)
        )

        // Dropdown de Tiempo
        DropdownSelector(
            text = "Tiemp",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun DropdownSelector(
    text: String,
    modifier: Modifier = Modifier,
    options: List<String> = emptyList(),
    onSelectionChange: (String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            Text(
                text = text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null
            )
        }

        // Implementación del DropdownMenu (comentado para simplificar)
        /*
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelectionChange(option)
                        expanded = false
                    }
                )
            }
        }
        */
    }
}