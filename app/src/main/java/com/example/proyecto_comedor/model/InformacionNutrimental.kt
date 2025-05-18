package com.example.proyecto_comedor.model



import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "informacion_nutrimental", strict = false)
data class InformacionNutrimental(
    @field:Element(name = "kcal", required = false)
    var kcal: Int = 0,

    @field:Element(name = "hc", required = false)
    var hc: Int = 0,

    @field:Element(name = "p", required = false)
    var p: Int = 0,

    @field:Element(name = "l", required = false)
    var l: Int = 0
)