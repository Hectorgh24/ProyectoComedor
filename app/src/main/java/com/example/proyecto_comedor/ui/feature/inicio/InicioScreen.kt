package com.example.proyecto_comedor.ui.feature.inicio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import com.example.proyecto_comedor.ui.components.SearchBarSelector
import com.example.proyecto_comedor.ui.components.MenuTabs
import kotlinx.coroutines.launch
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/*@Composable
fun InicioScreen() {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        // Contenido principal
        Column(
            modifier = Modifier.fillMaxSize()

        ) {
            // Usar el selector de barra de búsqueda que elige la implementación adecuada
            // según la versión de Android
            SearchBarSelector(
                drawerState = drawerState,
                onSearch = { searchText ->
                    // Aquí puedes implementar la lógica de búsqueda
                    // Por ejemplo, filtrar elementos o navegar a una pantalla de resultados
                    println("Búsqueda: $searchText")
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            MenuTabs()
        }
}*/
@Composable
fun InicioScreen() {



        Spacer(modifier = Modifier.height(8.dp))

        MenuTabs()

}

@Preview(showBackground = true)
@Composable
fun PreviewInicioScreen() {
    InicioScreen()
}
