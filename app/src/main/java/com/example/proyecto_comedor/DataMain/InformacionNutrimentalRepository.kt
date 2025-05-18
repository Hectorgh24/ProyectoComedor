package com.example.proyecto_comedor.DataMain

import android.util.Log
import com.example.proyecto_comedor.model.InformacionNutrimental
import com.example.proyecto_comedor.network.MenuApiService2
import java.text.SimpleDateFormat
import java.util.*


interface InformacionNutrimentalRepository {
    /** Obtiene la info nutrimental de desayuno y comida para hoy */
    suspend fun getInfo(): Pair<InformacionNutrimental?, InformacionNutrimental?>
}

class NetworkInformacionNutrimentalRepository(
    private val api: MenuApiService2
) : InformacionNutrimentalRepository {

    override suspend fun getInfo(): Pair<InformacionNutrimental?, InformacionNutrimental?> {
        val fecha = obtenerFechaActual()

        val respDes = api.getDesayuno(fecha)
        Log.d("RepoInfo", "Desayuno HTTP code=${respDes.code()}")
        val infoDes = respDes.body().takeIf { respDes.isSuccessful }

        val respCom = api.getComida(fecha)
        Log.d("RepoInfo", "Comida HTTP code=${respCom.code()}")
        val infoCom = respCom.body().takeIf { respCom.isSuccessful }

        return Pair(infoDes, infoCom)
    }

    private fun obtenerFechaActual(): String {
        val fmt = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return fmt.format(Date())
    }
}