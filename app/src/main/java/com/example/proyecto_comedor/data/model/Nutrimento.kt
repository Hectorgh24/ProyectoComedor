package com.example.proyecto_comedor.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "nutrimento", strict = false)
data class Nutrimento(
    @field:Element(name = "id", required = false)
    var id: Int? = null,

    @field:Element(name = "id_desayuno_comida", required = false)
    var idDesayunoComida: Int? = null,

    @field:Element(name = "kcal", required = false)
    var kcal: Int? = null,

    @field:Element(name = "hc", required = false)
    var hc: Int? = null,

    @field:Element(name = "p", required = false)
    var p: Int? = null,

    @field:Element(name = "l", required = false)
    var l: Int? = null
)