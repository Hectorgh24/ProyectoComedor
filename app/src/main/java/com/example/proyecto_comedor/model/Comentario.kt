package com.example.proyecto_comedor.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "comentario", strict = false)
data class Comentario @JvmOverloads constructor(
    @field:Element(name = "id_desayuno_comida", required = true)
    var idDesayunoComida: Int = 0,

    @field:Element(name = "sabor", required = true)
    var sabor: Int = 0,

    @field:Element(name = "porcion", required = true)
    var porcion: Int = 0,

    @field:Element(name = "precio", required = true)
    var precio: Int = 0,

    @field:Element(name = "satisfaccion_general", required = true)
    var satisfaccionGeneral: Int = 0
)

@Root(name = "respuesta", strict = false)
data class ComentarioResponse @JvmOverloads constructor(
    @field:Element(name = "id", required = false)
    var id: Int = 0,

    @field:Element(name = "mensaje", required = false)
    var mensaje: String = ""
) 