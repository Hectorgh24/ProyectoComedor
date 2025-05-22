package com.example.proyecto_comedor.model

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.ElementListUnion
import org.simpleframework.xml.Root

@Root(name = "producto_carta", strict = false)
data class ProductListResponse(
    @field:ElementListUnion(
        value = [
            ElementList(entry = "platillo",          inline = true, type = Product::class),
            ElementList(entry = "sandwich_torta",    inline = true, type = Product::class),
            ElementList(entry = "sugerencia",        inline = true, type = Product::class),
            ElementList(entry = "postre",            inline = true, type = Product::class),
            ElementList(entry = "bebida",            inline = true, type = Product::class),
            ElementList(entry = "ingrediente_extra", inline = true, type = Product::class),
            ElementList(entry = "otro",              inline = true, type = Product::class)
        ]
    )
    var items: MutableList<Product> = mutableListOf()
)