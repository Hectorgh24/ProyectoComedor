package com.example.proyecto_comedor.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false)
data class Product @JvmOverloads constructor(
    @field:Element(name = "id",            required = false)
    var id: Int = 0,

    @field:Element(name = "id_categoria", required = false)
    var categoryId: Int = 0,

    @field:Element(name = "nombre",        required = false)
    var nombre: String = "",

    @field:Element(name = "descripcion",   required = false)
    var descripcion: String = "",

    @field:Element(name = "precio",        required = false)
    var precio: Double = 0.0,

    @field:Element(name = "img_url",       required = false)
    var imgUrl: String = ""
)

