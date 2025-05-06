package com.example.proyecto_comedor.data.model

data class Comedor(
    val nombre: String,
    val ubicacion: String,
    val region: String,
    val mapsUrl: String,
    val coordenadas: String? = null
)
