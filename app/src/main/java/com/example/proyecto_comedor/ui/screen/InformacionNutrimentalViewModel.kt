package com.example.proyecto_comedor.ui.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_comedor.DataMain.InformacionNutrimentalRepository
import com.example.proyecto_comedor.MenuDelDiaApplication
import com.example.proyecto_comedor.model.InformacionNutrimental
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

data class InformacionNutrimentalUiState(
    val desayuno: InformacionNutrimental? = null,
    val comida: InformacionNutrimental? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)

class InformacionNutrimentalViewModel(
    private val repo: InformacionNutrimentalRepository
) : ViewModel() {

    var uiState by mutableStateOf(InformacionNutrimentalUiState(isLoading = true))
        private set

    init {
        loadInfo()
    }

    private fun loadInfo() {
        viewModelScope.launch {
            uiState = InformacionNutrimentalUiState(isLoading = true)
            try {
                val (infoDesayuno, infoComida) = repo.getInfo()
                uiState = InformacionNutrimentalUiState(
                    desayuno = infoDesayuno,
                    comida   = infoComida,
                    isLoading = false
                )
                Log.d("InfoVM", "Loaded info: desayuno=$infoDesayuno, comida=$infoComida")
            } catch (e: IOException) {
                Log.e("InfoVM", "Network error: ${e.localizedMessage}", e)
                uiState = InformacionNutrimentalUiState(isError = true)
            } catch (e: HttpException) {
                Log.e("InfoVM", "HTTP error: ${e.code()}", e)
                uiState = InformacionNutrimentalUiState(isError = true)
            } catch (e: Exception) {
                Log.e("InfoVM", "Unexpected error: ${e.localizedMessage}", e)
                uiState = InformacionNutrimentalUiState(isError = true)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as MenuDelDiaApplication)
                val repoInfo = app.container2.informacionNutrimentalRepository
                InformacionNutrimentalViewModel(repoInfo)
            }
        }
    }
}