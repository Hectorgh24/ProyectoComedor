package com.example.proyecto_comedor.DataMain

import android.util.Log
import com.example.proyecto_comedor.model.MenuDelDia
import com.example.proyecto_comedor.network.MenuApiService
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

interface MenuDelDiaRepository {
    suspend fun getMenus(): List<MenuDelDia>
}

// DataMain/MenuDelDiaRepository.kt
class NetworkMenuDelDiaRepository(
    private val api: MenuApiService
) : MenuDelDiaRepository {

    override suspend fun getMenus(): List<MenuDelDia> {
        val fecha = obtenerFechaActual()
        // Llamada desayuno
        val respDesayuno = api.getDesayuno(fecha)
        val listaDesayuno = if (respDesayuno.isSuccessful && respDesayuno.body() != null)
            respDesayuno.body()!!.menus
        else {
            Log.e("Repo", "Desayuno 404 o body nulo")
            emptyList()
        }

        // Llamada comida
        val respComida = api.getComida(fecha)
        val listaComida = if (respComida.isSuccessful && respComida.body() != null)
            respComida.body()!!.menus
        else {
            Log.e("Repo", "Comida 404 o body nulo")
            emptyList()
        }

        // Combina: desayuno primero, luego comida
        return listaDesayuno + listaComida
    }

    private fun obtenerFechaActual(): String {
        val fmt = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return fmt.format(Date())
    }
}