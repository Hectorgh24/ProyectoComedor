package com.example.proyecto_comedor.ui.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_comedor.DataMain.MenuDelDiaRepository
import com.example.proyecto_comedor.MenuDelDiaApplication
import com.example.proyecto_comedor.data.model.FoodItem
import com.example.proyecto_comedor.model.MenuDelDia
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

data class MenuDelDiaUiState(
    val desayuno: FoodItem? = null,
    val comida: FoodItem? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)

class MenuDelDiaViewModel(
    private val repo: MenuDelDiaRepository
) : ViewModel() {

    var uiState by mutableStateOf(MenuDelDiaUiState(isLoading = true))
        private set

    init { loadMenu() }

    private fun loadMenu() {
        viewModelScope.launch {
            uiState = MenuDelDiaUiState(isLoading = true)
            try {
                val list = repo.getMenus()
                Log.d("ViewModel", "Lista recibida de repo: tamaño=${list.size}, contenido=$list")
                val desayuno = list.firstOrNull()
                val comida   = list.getOrNull(1) ?: desayuno
                uiState = MenuDelDiaUiState(
                    desayuno  = desayuno?.toFoodItem(),
                    comida    = comida?.toFoodItem(),
                    isLoading = false
                )
                Log.d("ViewModel", "→ uiState updated: $uiState")
            } catch (e: IOException) {
                Log.e("ViewModel", "Error de red: ${e.localizedMessage}", e)
                uiState = MenuDelDiaUiState(isError = true)
            } catch (e: HttpException) {
                Log.e("ViewModel", "Error HTTP: code=${e.code()}, msg=${e.message()}", e)
                uiState = MenuDelDiaUiState(isError = true)
            } catch (e: Exception) {
                Log.e("ViewModel", "Error inesperado: ${e.localizedMessage}", e)
                uiState = MenuDelDiaUiState(isError = true)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as MenuDelDiaApplication)
                MenuDelDiaViewModel(app.container.menuDelDiaRepository)
            }
        }
    }
}

private fun MenuDelDia.toFoodItem() = FoodItem(
    name        = tipo,
    imageUrl    = imgUrl,
    description = descripcion
)
