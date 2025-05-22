package com.example.proyecto_comedor.ui.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.proyecto_comedor.DataMain.ComentarioRepository
import com.example.proyecto_comedor.model.Comentario
import kotlinx.coroutines.launch

data class ComentarioUiState(
    val idDesayunoComida: Int = 0,
    val sabor: Int = 0,
    val porcion: Int = 0,
    val precio: Int = 0,
    val satisfaccionGeneral: Int = 0,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)

class ComentarioViewModel(
    private val comentarioRepository: ComentarioRepository
) : ViewModel() {

    var uiState by mutableStateOf(ComentarioUiState())
        private set

    fun actualizarIdDesayunoComida(id: Int) {
        uiState = uiState.copy(idDesayunoComida = id)
        Log.d("ComentarioViewModel", "ID actualizado: $id")
    }

    fun actualizarSabor(sabor: Int) {
        uiState = uiState.copy(sabor = sabor)
        Log.d("ComentarioViewModel", "Sabor actualizado: $sabor")
    }

    fun actualizarPorcion(porcion: Int) {
        uiState = uiState.copy(porcion = porcion)
        Log.d("ComentarioViewModel", "Porción actualizada: $porcion")
    }

    fun actualizarPrecio(precio: Int) {
        uiState = uiState.copy(precio = precio)
        Log.d("ComentarioViewModel", "Precio actualizado: $precio")
    }

    fun actualizarSatisfaccionGeneral(satisfaccion: Int) {
        uiState = uiState.copy(satisfaccionGeneral = satisfaccion)
        Log.d("ComentarioViewModel", "Satisfacción actualizada: $satisfaccion")
    }

    fun enviarComentario() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, isError = false, isSuccess = false)
            
            val comentario = Comentario(
                idDesayunoComida = uiState.idDesayunoComida,
                sabor = uiState.sabor,
                porcion = uiState.porcion,
                precio = uiState.precio,
                satisfaccionGeneral = uiState.satisfaccionGeneral
            )

            Log.d("ComentarioViewModel", "Enviando comentario: $comentario")

            comentarioRepository.enviarComentario(comentario)
                .onSuccess { response ->
                    Log.d("ComentarioViewModel", "Comentario enviado exitosamente: $response")
                    uiState = uiState.copy(
                        isLoading = false,
                        isSuccess = true,
                        isError = false
                    )
                }
                .onFailure { exception ->
                    Log.e("ComentarioViewModel", "Error al enviar comentario: ${exception.message}")
                    uiState = uiState.copy(
                        isLoading = false,
                        isSuccess = false,
                        isError = true,
                        errorMessage = exception.message ?: "Error desconocido"
                    )
                }
        }
    }

    fun resetState() {
        uiState = ComentarioUiState()
    }

    companion object {
        fun provideFactory(comentarioRepository: ComentarioRepository): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                ComentarioViewModel(comentarioRepository)
            }
        }
    }
} 