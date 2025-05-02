package com.example.proyecto_comedor.ui.components


import android.os.Build
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable

/**
 * Componente que selecciona la barra de búsqueda adecuada según la versión de Android.
 * Para dispositivos con Android 8.x (API 26) o inferior, utiliza una barra de búsqueda personalizada.
 * Para versiones más recientes, utiliza la barra de búsqueda de Material 3.
 */
@Composable
fun SearchBarSelector(
    drawerState: DrawerState,
    onSearch: (String) -> Unit = {}
) {
    // Usar la barra de búsqueda personalizada para Android 8.x (API 26) e inferior
    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) {
        CustomSearchBar(drawerState = drawerState, onSearch = onSearch)
    } else {
        // Usar la barra de búsqueda de Material 3 para versiones más recientes
        FoodSearchBar(drawerState = drawerState)
    }
}