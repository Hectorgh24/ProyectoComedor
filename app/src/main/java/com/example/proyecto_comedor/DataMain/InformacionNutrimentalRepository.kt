package com.example.proyecto_comedor.DataMain

import android.util.Log
import com.example.proyecto_comedor.model.InformacionNutrimental
import com.example.proyecto_comedor.network.MenuApiService2
import java.text.SimpleDateFormat
import java.util.*

interface InformacionNutrimentalRepository {
    suspend fun getInfo(): Pair<InformacionNutrimental?, InformacionNutrimental?>
}

class NetworkInformacionNutrimentalRepository(
    private val api: MenuApiService2
) : InformacionNutrimentalRepository {

    override suspend fun getInfo(): Pair<InformacionNutrimental?, InformacionNutrimental?> {
        val fecha = obtenerFechaLaborable()
        Log.d("RepoInfo", "→ Fecha usada: $fecha")

        val respDes = api.getDesayuno(fecha)
        Log.d("RepoInfo", "Desayuno HTTP code=${respDes.code()}, body=${respDes.body()}")

        val respCom = api.getComida(fecha)
        Log.d("RepoInfo", "Comida HTTP code=${respCom.code()}, body=${respCom.body()}")

        val infoDes = respDes.body().takeIf { it != null && respDes.isSuccessful }
        val infoCom = respCom.body().takeIf { it != null && respCom.isSuccessful }

        return Pair(infoDes, infoCom)
    }

    private fun obtenerFechaLaborable(): String {
        val cal = Calendar.getInstance(Locale.US)
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) cal.add(Calendar.DAY_OF_MONTH, -1)
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)   cal.add(Calendar.DAY_OF_MONTH, -2)
        return SimpleDateFormat("yyyy-MM-dd", Locale.US).format(cal.time)
    }
}