package com.example.proyecto_comedor.network

import com.example.proyecto_comedor.model.Comentario
import com.example.proyecto_comedor.model.ComentarioResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ComentarioApiService {
    @Headers("Content-Type: application/xml")
    @POST("api/comedores/comentarios/crear")
    suspend fun enviarComentario(@Body comentario: Comentario): Response<ComentarioResponse>
} 