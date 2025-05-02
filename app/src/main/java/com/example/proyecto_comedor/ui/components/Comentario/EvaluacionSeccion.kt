import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    // Row deslizable horizontalmente
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()) // <- Esto permite deslizar
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DropdownSelector(label = "Precio", options = listOf("Bajo", "Medio", "Alto"))
        DropdownSelector(label = "Porción", options = listOf("Poca", "Adecuada", "Mucha"))
        DropdownSelector(label = "Sabor", options = listOf("Malo", "Regular", "Bueno", "Excelente"))
        DropdownSelector(label = "Tiempo", options = listOf("Rápido", "Aceptable", "Lento"))
        // Puedes agregar más Chips aquí si quieres
    }
}

@Composable
fun DropdownSelector(
    label: String,
    modifier: Modifier = Modifier,
    options: List<String> = emptyList(),
    onSelectionChange: (String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf<String?>(null) }

    Box(modifier = modifier) {
        AssistChip(
            onClick = { expanded = true },
            label = {
                Text(
                    text = selectedOption ?: label,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    //tint = Color(0xFF2e7d32)
                )
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selectedOption = option
                        onSelectionChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
