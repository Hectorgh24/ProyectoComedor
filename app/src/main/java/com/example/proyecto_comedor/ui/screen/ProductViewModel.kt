package com.example.proyecto_comedor.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_comedor.DataMain.ProductRepository
import com.example.proyecto_comedor.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repo: ProductRepository,
    private val categoria: String
) : ViewModel() {

    private val _items = MutableStateFlow<List<Product>>(emptyList())
    val items: StateFlow<List<Product>> = _items

    init { load() }

    private fun load() = viewModelScope.launch {
        _items.value = when(categoria) {
            "Platillos"            -> repo.fetchPlatillos()
            "Sandwiches y Tortas"  -> repo.fetchSandwiches()
            "Sugerencia del Chef"  -> repo.fetchPlatAdvice()
            "Postres"              -> repo.fetchPostres()
            "Bebidas"              -> repo.fetchBebidas()
            "Ingredientes Extra"   -> repo.fetchExtras()
            "otros"                -> repo.fetchOtros()
            else                   -> emptyList()
        }
    }
}
