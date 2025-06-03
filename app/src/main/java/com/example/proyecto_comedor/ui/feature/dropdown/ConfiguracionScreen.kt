package com.example.proyecto_comedor.ui.feature.dropdown

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfiguracionScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    // Estados para los switches
    var modoOscuroActivado by remember { mutableStateOf(false) }
    var notificacionesActivadas by remember { mutableStateOf(true) }
    var sonidosActivados by remember { mutableStateOf(true) }
    var vibracionActivada by remember { mutableStateOf(true) }
    var sincronizacionAutomatica by remember { mutableStateOf(true) }
    var ahorroEnergia by remember { mutableStateOf(false) }
    var actualizacionesAutomaticas by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Configuración")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Notificaciones",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            ConfiguracionItem(
                icono = Icons.Default.Notifications,
                titulo = "Notificaciones push",
                descripcion = "Recibe notificaciones de la aplicación",
                isChecked = notificacionesActivadas,
                onCheckedChange = { notificacionesActivadas = it }
            )

            ConfiguracionItem(
                icono = Icons.Default.VolumeUp,
                titulo = "Sonidos",
                descripcion = "Reproduce sonidos para notificaciones",
                isChecked = sonidosActivados,
                onCheckedChange = { sonidosActivados = it }
            )

            ConfiguracionItem(
                icono = Icons.Default.Vibration,
                titulo = "Vibración",
                descripcion = "Vibra al recibir notificaciones",
                isChecked = vibracionActivada,
                onCheckedChange = { vibracionActivada = it }
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // Sección: Datos y Sincronización
            Text(
                text = "Datos y Sincronización",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            ConfiguracionItem(
                icono = Icons.Default.Sync,
                titulo = "Sincronización automática",
                descripcion = "Sincroniza datos automáticamente",
                isChecked = sincronizacionAutomatica,
                onCheckedChange = { sincronizacionAutomatica = it }
            )

            ConfiguracionItem(
                icono = Icons.Default.SystemUpdateAlt,
                titulo = "Actualizaciones automáticas",
                descripcion = "Descargar actualizaciones automáticamente",
                isChecked = actualizacionesAutomaticas,
                onCheckedChange = { actualizacionesAutomaticas = it }
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // Sección: Sistema
            Text(
                text = "Sistema",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            ConfiguracionItem(
                icono = Icons.Default.BatteryChargingFull,
                titulo = "Modo ahorro de energía",
                descripcion = "Reduce el consumo de batería",
                isChecked = ahorroEnergia,
                onCheckedChange = { ahorroEnergia = it }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón de restaurar configuraciones
            OutlinedButton(
                onClick = {
                    // Restaurar valores por defecto
                    modoOscuroActivado = false
                    notificacionesActivadas = true
                    sonidosActivados = true
                    vibracionActivada = true
                    sincronizacionAutomatica = true
                    ahorroEnergia = false
                    actualizacionesAutomaticas = true
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.RestartAlt,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Restaurar configuración por defecto")
            }
        }
    }
}

@Composable
fun ConfiguracionItem(
    icono: androidx.compose.ui.graphics.vector.ImageVector,
    titulo: String,
    descripcion: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icono,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = descripcion,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Switch(
                checked = isChecked,
                onCheckedChange = onCheckedChange
            )
        }
    }
}