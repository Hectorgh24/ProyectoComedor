package com.example.proyecto_comedor.DataMain

import com.example.proyecto_comedor.network.ComentarioApiService
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

interface AppContainer4 {
    val comentarioRepository: ComentarioRepository
}

class DefaultAppContainer4 : AppContainer4 {

    private val baseUrl = "https://vmonge.me/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()

    private val retrofitService: ComentarioApiService by lazy {
        retrofit.create(ComentarioApiService::class.java)
    }

    override val comentarioRepository: ComentarioRepository by lazy {
        NetworkComentarioRepository(retrofitService)
    }
} 