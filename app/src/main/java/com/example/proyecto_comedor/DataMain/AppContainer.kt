package com.example.proyecto_comedor.DataMain

import com.example.proyecto_comedor.network.MenuApiService
import com.example.proyecto_comedor.DataMain.MenuDelDiaRepository
import com.example.proyecto_comedor.DataMain.NetworkMenuDelDiaRepository
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory


interface AppContainer {
    val menuDelDiaRepository: MenuDelDiaRepository
}

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