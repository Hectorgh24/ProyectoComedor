package com.example.proyecto_comedor.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_comedor.DataMain.ProductRepository

class ProductViewModelFactory(
    private val repo: ProductRepository,
    private val categoria: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(repo, categoria) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}