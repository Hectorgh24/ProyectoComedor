package com.example.proyecto_comedor.ui.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.proyecto_comedor.MenuDelDiaApplication
import com.example.proyecto_comedor.model.Product
import com.example.proyecto_comedor.ui.screen.ProductViewModel
import com.example.proyecto_comedor.ui.screen.ProductViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriaDetalleScreen(
    nombreCategoria: String,
    navController: NavController
) {
    // Accede al Application para obtener container3.productRepository
    val context = LocalContext.current.applicationContext as MenuDelDiaApplication
    val factory = ProductViewModelFactory(
        repo = context.container3.productRepository,
        categoria = nombreCategoria
    )
    val viewModel: ProductViewModel = viewModel(factory = factory)

    val items by viewModel.items.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(nombreCategoria) })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { product ->
                ProductCard(
                    nombre      = product.nombre,
                    descripcion = product.descripcion,
                    precio      = product.precio.toString(),
                    imageUrl    = product.imgUrl
                )
            }
        }
    }
}

