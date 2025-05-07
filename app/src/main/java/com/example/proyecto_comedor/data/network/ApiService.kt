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

    /* Ejemplo de métodos para operaciones CRUD
    @GET("comedores")
    suspend fun getComedores(): List<DataClassModel>

    @GET("comedores/{id}")
    suspend fun getComedor(@Path("id") id: String): DataClassModel

    @POST("comedores")
    suspend fun createComedor(@Body comedor: DataClassModel: DataClassModel

    */

    suspend fun getComedores(): List<ApiResponse>
}