package com.example.proyecto_comedor.network

import com.example.proyecto_comedor.model.InformacionNutrimental
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MenuApiService2 {
    @GET("api/comedores/informacionNutrimental/obtenerInformacionNutrimentalDesayunoPorFecha/{fecha}")
    suspend fun getDesayuno(
        @Path("fecha") fecha: String
    ): Response<InformacionNutrimental>

    @GET("api/comedores/informacionNutrimental/obtenerInformacionNutrimentalComidaPorFecha/{fecha}")
    suspend fun getComida(
        @Path("fecha") fecha: String
    ): Response<InformacionNutrimental>
}