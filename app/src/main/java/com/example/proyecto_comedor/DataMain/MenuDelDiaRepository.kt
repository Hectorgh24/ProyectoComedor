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
        val fecha = obtenerFechaLaborable()
        // Llamada desayuno
        val respDesayuno = api.getDesayuno(fecha)
        val listaDesayuno = if (respDesayuno.isSuccessful && respDesayuno.body() != null) {
            respDesayuno.body()!!.menus
        } else {
            Log.e("Repo", "Desayuno 404 o body nulo para fecha $fecha")
            emptyList()
        }

        // Llamada comida
        val respComida = api.getComida(fecha)
        val listaComida = if (respComida.isSuccessful && respComida.body() != null) {
            respComida.body()!!.menus
        } else {
            Log.e("Repo", "Comida 404 o body nulo para fecha $fecha")
            emptyList()
        }

        // Combina: desayuno primero, luego comida
        return listaDesayuno + listaComida
    }

    /**
     * Devuelve la fecha en "yyyy-MM-dd":
     * - Si hoy es sábado o domingo, retrocede hasta el viernes anterior.
     * - En cualquier otro caso, usa la fecha de hoy.
     */
    private fun obtenerFechaLaborable(): String {
        val cal = Calendar.getInstance(Locale.US)
        when (cal.get(Calendar.DAY_OF_WEEK)) {
            Calendar.SATURDAY -> cal.add(Calendar.DAY_OF_MONTH, -1)
            Calendar.SUNDAY   -> cal.add(Calendar.DAY_OF_MONTH, -2)
        }
        val formato = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return formato.format(cal.time)
    }
}