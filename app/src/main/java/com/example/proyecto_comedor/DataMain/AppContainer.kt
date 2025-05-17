package com.example.proyecto_comedor.DataMain

import com.example.proyecto_comedor.network.MenuApiService
import com.example.proyecto_comedor.DataMain.MenuDelDiaRepository
import com.example.proyecto_comedor.DataMain.NetworkMenuDelDiaRepository
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

/**
 * Contenedor de inyección de dependencias a nivel de aplicación.
 */
interface AppContainer {
    val menuDelDiaRepository: MenuDelDiaRepository
}

/**
 * Implementación del contenedor de dependencias.
 *
 * Los objetos son inicializados de forma perezosa y comparten la misma instancia durante toda la aplicación.
 */
class DefaultAppContainer : AppContainer {

    private val baseUrl = "https://vmonge.me/" // 10.0.2.2 es localhost para el emulador Android

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()

    private val retrofitService: MenuApiService by lazy {
        retrofit.create(MenuApiService::class.java)
    }

    override val menuDelDiaRepository: MenuDelDiaRepository by lazy {
        NetworkMenuDelDiaRepository(retrofitService)
    }
}