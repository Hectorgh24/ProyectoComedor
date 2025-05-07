package com.example.proyecto_comedor.data.network

import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

/**
 * Objeto `RetrofitClient` que proporciona una instancia de Retrofit configurada
 * para comunicarse con el servidor web mediante solicitudes HTTP.
 */
object RetrofitClient {
    // URL base del servidor
    private const val BASE_URL = "http://localhost:8080/api/comedores/"


    //Instancia de Retrofit configurada con el convertidor SimpleXML.
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    /*
     * Servicio API que define cómo Retrofit se comunica con el servidor web.
     * Este servicio se inicializa de forma perezosa (lazy), para que no se inicialice
     * hasta que se necesite y solo se inicialice una vez
     */
    val apiService: ApiService by lazy {
        // En ComedoresApiService.kt definimos los métodos de la API
        retrofit.create(ApiService::class.java)
    }
}