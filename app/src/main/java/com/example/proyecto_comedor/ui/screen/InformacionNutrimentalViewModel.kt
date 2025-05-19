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

    init { loadInfo() }

    private fun loadInfo() {
        viewModelScope.launch {
            Log.d("InfoVM", ">>>> iniciando loadInfo()")
            uiState = InformacionNutrimentalUiState(isLoading = true)
            try {
                val (infoDes, infoCom) = repo.getInfo()
                Log.d("InfoVM", " Repo devolvió desayuno=$infoDes, comida=$infoCom")
                uiState = InformacionNutrimentalUiState(
                    desayuno = infoDes,
                    comida   = infoCom,
                    isLoading = false
                )
            } catch (e: Exception) {
                Log.e("InfoVM", "Error loadInfo(): ${e.localizedMessage}", e)
                uiState = InformacionNutrimentalUiState(isError = true)
            }
            Log.d("InfoVM", ">>>> loadInfo() terminó con uiState=$uiState")
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MenuDelDiaApplication)
                val repo = app.container2.informacionNutrimentalRepository
                InformacionNutrimentalViewModel(repo)
            }
        }
    }
}