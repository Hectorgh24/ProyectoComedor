package com.example.proyecto_comedor.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "desayuno_comida", strict = false)
data class DesayunoComida(
    @field:Element(name = "id", required = false)
    var id: Int? = null,

    @field:Element(name = "tipo", required = false)
    var tipo: String? = null,

    @field:Element(name = "fecha", required = false)
    var fecha: String? = null,

    @field:Element(name = "descripcion", required = false)
    var descripcion: String? = null,

    @field:Element(name = "img_url", required = false)
    var imgUrl: String? = null
)