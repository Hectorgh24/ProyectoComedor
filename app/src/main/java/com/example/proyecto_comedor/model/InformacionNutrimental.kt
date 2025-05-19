package com.example.proyecto_comedor.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Representa la información nutrimental de un platillo.
 * Le añadimos @JvmOverloads para que SimpleXML encuentre un constructor
 * con valores por defecto y required=false para aceptar nodos ausentes.
 */
@Root(name = "informacion_nutrimental", strict = false)
data class InformacionNutrimental @JvmOverloads constructor(
    @field:Element(name = "kcal", required = false)
    var kcal: Int = 0,

    @field:Element(name = "hc", required = false)
    var hc: Int = 0,

    @field:Element(name = "p", required = false)
    var p: Int = 0,

    @field:Element(name = "l", required = false)
    var l: Int = 0
)
