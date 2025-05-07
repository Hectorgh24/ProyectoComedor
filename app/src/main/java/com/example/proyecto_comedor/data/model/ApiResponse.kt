package com.example.proyecto_comedor.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * Clases de datos que representan las respuesta de la API.
 * Utiliza Simple XML para el mapeo XML a objetos Kotlin.
 */

/* Ejemplo
@Root(name = "nombreElmentoXMLRaiz", strict = false)
data class nombreObjetoXml(
    @field: Element(name = "nombreElementoXML", required = "false")
    var nombreElementoXML: TipoDato? = null,

    . . .

    @field: ElementList(name = "nombreElementoXML", inline = true, required = false)
    var menu: List<TipoDato>? = null
)
*/

@Root(name = "response", strict = false)
data class ApiResponse(
    @field:ElementList(name = "comedores", inline = true, required = false)
    var comedores: List<String>? = null
)