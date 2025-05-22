package com.example.proyecto_comedor.network

import com.example.proyecto_comedor.model.ProductListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiService {
    @GET("api/comedores/producto_carta/obtenerPlatillos")
    suspend fun getPlatillos(): Response<ProductListResponse>

    @GET("api/comedores/producto_carta/obtenerSandwichesTortas")
    suspend fun getSandwiches(): Response<ProductListResponse>

    @GET("api/comedores/producto_carta/obtenerSugerenciaChef")
    suspend fun getSugerenciaChef(): Response<ProductListResponse>

    @GET("api/comedores/producto_carta/obtenerPostres")
    suspend fun getPostres(): Response<ProductListResponse>

    @GET("api/comedores/producto_carta/obtenerBebidas")
    suspend fun getBebidas(): Response<ProductListResponse>

    @GET("api/comedores/producto_carta/obtenerIngredienteExtra")
    suspend fun getExtras(): Response<ProductListResponse>

    @GET("api/comedores/producto_carta/obtenerOtros")
    suspend fun getOtros(): Response<ProductListResponse>
}
