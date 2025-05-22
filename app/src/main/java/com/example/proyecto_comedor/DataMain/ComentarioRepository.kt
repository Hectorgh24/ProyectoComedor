package com.example.proyecto_comedor.DataMain

import android.util.Log
import com.example.proyecto_comedor.model.Comentario
import com.example.proyecto_comedor.model.ComentarioResponse
import com.example.proyecto_comedor.network.ComentarioApiService

interface ComentarioRepository {
    suspend fun enviarComentario(comentario: Comentario): Result<ComentarioResponse>
}

class NetworkComentarioRepository(
    private val api: ComentarioApiService
) : ComentarioRepository {

    override suspend fun enviarComentario(comentario: Comentario): Result<ComentarioResponse> {
        return try {
            Log.d("ComentarioRepo", "Enviando comentario: $comentario")
            val response = api.enviarComentario(comentario)
            
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Log.d("ComentarioRepo", "Comentario enviado exitosamente: $body")
                    Result.success(body)
                } else {
                    Log.e("ComentarioRepo", "Respuesta exitosa pero body es null")
                    Result.failure(Exception("Respuesta vacía del servidor"))
                }
            } else {
                Log.e("ComentarioRepo", "Error al enviar comentario: ${response.code()} - ${response.message()}")
                Result.failure(Exception("Error del servidor: ${response.code()}"))
            }
        } catch (e: Exception) {
            Log.e("ComentarioRepo", "Excepción al enviar comentario: ${e.message}", e)
            Result.failure(e)
        }
    }
} 