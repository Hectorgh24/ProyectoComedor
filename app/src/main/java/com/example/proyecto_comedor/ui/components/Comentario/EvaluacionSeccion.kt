import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun EvaluacionSeccion(
    onPrecioSelected: (Int) -> Unit = {},
    onPorcionSelected: (Int) -> Unit = {},
    onSaborSelected: (Int) -> Unit = {}
) {
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
        DropdownSelector(
            label = "Precio",
            options = listOf("Barato", "Justo", "Caro"),
            onSelectionChange = { value ->
                val index = listOf("Barato", "Justo", "Caro").indexOf(value) + 1
                onPrecioSelected(index)
            }
        )
        DropdownSelector(
            label = "Porción",
            options = listOf("Poca", "Adecuada", "Generosa"),
            onSelectionChange = { value ->
                val index = listOf("Poca", "Adecuada", "Generosa").indexOf(value) + 1
                onPorcionSelected(index)
            }
        )
        DropdownSelector(
            label = "Sabor",
            options = listOf("Malo", "Regular", "Bueno", "Excelente"),
            onSelectionChange = { value ->
                val index = listOf("Malo", "Regular", "Bueno", "Excelente").indexOf(value) + 1
                onSaborSelected(index)
            }
        )
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
