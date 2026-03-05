package es.ies.ejercicios.u6.ej65.dip

import es.ies.ejercicios.u6.ej64.Resumible

/**
 * DIP - Versión refactorizada
 *
 * Ahora el módulo de alto nivel (InformeAppService)
 * depende de una abstracción (GeneradorInforme)
 * y no de implementaciones concretas.
 */

/* ================================
   ABSTRACCIÓN (CONTRATO)
   ================================ */

interface GeneradorInforme {
    fun generar(titulo: String, items: List<Resumible>): String
}

/* ================================
   IMPLEMENTACIONES (BAJO NIVEL)
   ================================ */

class GeneradorInformeCsv : GeneradorInforme {

    override fun generar(titulo: String, items: List<Resumible>): String =
        buildString {
            appendLine("titulo,$titulo")
            appendLine("item")
            for (item in items) {
                appendLine(item.resumen().replace(",", ";"))
            }
        }
}

class GeneradorInformeMarkdown : GeneradorInforme {

    override fun generar(titulo: String, items: List<Resumible>): String =
        buildString {
            appendLine("# $titulo")
            for (item in items) {
                appendLine("- ${item.resumen()}")
            }
        }
}

/* ================================
   MÓDULO DE ALTO NIVEL
   ================================ */

class InformeAppService(
    private val generador: GeneradorInforme   // 🔥 Depende de abstracción
) {

    fun ejecutar() {

        val items = listOf<Resumible>(
            object : Resumible {
                override fun resumen(): String = "Elemento A"
            },
            object : Resumible {
                override fun resumen(): String = "Elemento B"
            },
        )

        val salida = generador.generar("Demo DIP", items)
        println(salida)
    }
}

/* ================================
   MAIN / COMPOSICIÓN
   ================================ */

fun main() {

    // Aquí decidimos la implementación concreta (detalle)
    val generador: GeneradorInforme = GeneradorInformeMarkdown()

    val app = InformeAppService(generador)
    app.ejecutar()
}

