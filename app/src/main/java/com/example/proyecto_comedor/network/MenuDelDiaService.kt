package com.example.proyecto_comedor.network

import com.example.proyecto_comedor.model.MenuDelDia
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

@Root(name = "desayunos_comidas", strict = false)
data class MenuResponse(
    // Cambiado a MutableList y mutableListOf()
    @field:ElementList(name = "desayuno_comida", inline = true)
    var menus: MutableList<MenuDelDia> = mutableListOf()
)

interface MenuApiService {
    @GET("api/comedores/desayunos_comidas/obtenerPorFechaDesayuno/{fecha}")
    suspend fun getDesayuno(
        @Path("fecha") fecha: String
    ): Response<MenuResponse>

    @GET("api/comedores/desayunos_comidas/obtenerPorFechaComida/{fecha}")
    suspend fun getComida(
        @Path("fecha") fecha: String
    ): Response<MenuResponse>
}
