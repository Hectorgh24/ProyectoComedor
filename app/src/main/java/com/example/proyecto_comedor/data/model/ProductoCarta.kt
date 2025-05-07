package com.example.proyecto_comedor.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "producto_carta", strict = false)
data class ProductoCarta(
    @field:Element(name = "id", required = false)
    var id: Int? = null,

    @field:Element(name = "id_categoria", required = false)
    var idCategoria: Int? = null,

    @field:Element(name = "nombre", required = false)
    var nombre: String? = null,

    @field:Element(name = "descripcion", required = false)
    var descripcion: String? = null,

    @field:Element(name = "precio", required = false)
    var precio: String? = null,

    @field:Element(name = "img_url", required = false)
    var imgUrl: String? = null
)
