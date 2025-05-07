package com.example.proyecto_comedor.data.network

import retrofit2.Response
import retrofit2.http.*
import com.example.proyecto_comedor.data.model.*

interface ApiService {

    /*
    Definir métodos para las operaciones CRUD
        @<metodo>("<ruta>")
        suspend fun <nombre función>(<parámetros>): <tipo de retorno>
    */

    @GET("desayunos_comidas/obtenerPorId/{id}")
    suspend fun getDesayunoComidaPoId(@Path("id") id: Int): DesayunoComida

    @GET("informacionNutrimental/obtenerInformacionNutrimentalPorId/{id}")
    suspend fun getInformacionNutrimentalPorId(@Path("id") id: Int): Nutrimento

    @GET("producto_carta/obtenerTodos")
    suspend fun getProductosCarta(): List<ProductoCarta>
}