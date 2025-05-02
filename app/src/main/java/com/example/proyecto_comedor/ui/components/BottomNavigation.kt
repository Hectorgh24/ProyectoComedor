package com.example.proyecto_comedor.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material.icons.outlined.VolunteerActivism
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

import androidx.compose.animation.Crossfade

@Composable
fun BottomNavigation(navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Inicio", "Beca")

    val icons = listOf(
        Icons.Outlined.Home to Icons.Filled.Home,
        Icons.Outlined.VolunteerActivism to Icons.Filled.VolunteerActivism
    )

    val routes = listOf("inicio_screen", "beca_screen")

    NavigationBar() {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Crossfade(targetState = selectedItem == index, label = "iconCrossfade") { isSelected ->
                        val icon = if (isSelected) icons[index].second else icons[index].first
                        Icon(
                            imageVector = icon,
                            contentDescription = item,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(routes[index]) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                /*colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color(0xFFd9e7ca) // Cambia este color por el que desees como fondo seleccionado
                )*/
            )
        }
    }
}

