package com.example.proyecto_comedor.DataMain

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import com.example.proyecto_comedor.network.MenuApiService2

/**
 * Contenedor DI para información nutrimental
 */
interface AppContainer2 {
    val informacionNutrimentalRepository: InformacionNutrimentalRepository
}

class DefaultAppContainer2 : AppContainer2 {
    private val baseUrl = "https://vmonge.me/"

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            // Log full request & response bodies (incluye XML)
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()

    private val service2: MenuApiService2 by lazy {
        retrofit.create(MenuApiService2::class.java)
    }

    override val informacionNutrimentalRepository: InformacionNutrimentalRepository by lazy {
        NetworkInformacionNutrimentalRepository(service2)
    }
}
