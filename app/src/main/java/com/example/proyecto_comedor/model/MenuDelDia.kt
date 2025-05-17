package com.example.proyecto_comedor.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "desayuno_comida", strict = false)
data class MenuDelDia(
    @field:Element(name = "id") var id: Int = 0,
    @field:Element(name = "tipo") var tipo: String = "",
    @field:Element(name = "fecha") var fecha: String = "",
    @field:Element(name = "descripcion") var descripcion: String = "",
    @field:Element(name = "img_url") var imgUrl: String = ""
)
