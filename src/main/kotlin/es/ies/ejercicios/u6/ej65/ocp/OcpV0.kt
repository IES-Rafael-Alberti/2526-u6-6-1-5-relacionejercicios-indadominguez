package es.ies.ejercicios.u6.ej65.ocp

import es.ies.ejercicios.u6.ej64.Resumible

/**
 * OCP - Versión refactorizada
 *
 * Ahora el generador depende de una abstracción (FormatoInforme)
 * y no necesita modificarse cuando añadimos nuevos formatos.
 */

/* ================================
   ABSTRACCIÓN
   ================================ */

interface FormatoInforme {
    fun generar(titulo: String, items: List<Resumible>): String
}

/* ================================
   IMPLEMENTACIONES CONCRETAS
   ================================ */

class FormatoCsv : FormatoInforme {

    override fun generar(titulo: String, items: List<Resumible>): String =
        buildString {
            appendLine("titulo,$titulo")
            appendLine("item")
            for (item in items) {
                appendLine(item.resumen().replace(",", ";"))
            }
        }
}

class FormatoMarkdown : FormatoInforme {

    override fun generar(titulo: String, items: List<Resumible>): String =
        buildString {
            appendLine("# $titulo")
            for (item in items) {
                appendLine("- ${item.resumen()}")
            }
        }
}

/* ================================
   GENERADOR (CERRADO A MODIFICACIÓN)
   ================================ */

class GeneradorInforme(
    private val formato: FormatoInforme
) {

    fun generar(titulo: String, items: List<Resumible>): String =
        formato.generar(titulo, items)
}

/* ================================
   MAIN / DEMO
   ================================ */

fun main() {

    val items = listOf<Resumible>(
        object : Resumible {
            override fun resumen(): String = "Elemento A"
        },
        object : Resumible {
            override fun resumen(): String = "Elemento B"
        },
    )

    // La decisión del formato se hace fuera del generador
    val formato: FormatoInforme = FormatoMarkdown()
    val generador = GeneradorInforme(formato)

    println(generador.generar("Demo OCP", items))
}