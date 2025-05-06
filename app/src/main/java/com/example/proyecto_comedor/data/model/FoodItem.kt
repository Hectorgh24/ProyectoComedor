package com.example.proyecto_comedor.data.model

data class FoodItem(
    val name: String,
    val imageRes: Int,
    val rating: Float,
    val reviewCount: Int,
    val accompaniments: List<String>
)
