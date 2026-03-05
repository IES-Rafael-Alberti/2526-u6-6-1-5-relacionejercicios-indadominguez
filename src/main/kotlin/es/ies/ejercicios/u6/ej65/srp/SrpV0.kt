package es.ies.ejercicios.u6.ej65.srp

import es.ies.ejercicios.u6.ej64.Alumno
import es.ies.ejercicios.u6.ej64.InformeMarkdown
import es.ies.ejercicios.u6.ej64.Persona
import es.ies.ejercicios.u6.ej64.RegistroPersonas
import es.ies.ejercicios.u6.ej64.Resumible

/**
 * RESPONSABILIDAD 1:
 * Proveer los datos
 */
class DataProvider {
    fun obtenerItems(): List<Resumible> =
        listOf(
            Persona(" Ana ", 20),
            Alumno("Luis", 19, "1DAM"),
            Persona("Marta", 18),
        )
}

/**
 * RESPONSABILIDAD 2:
 * Gestionar el registro de personas
 */
class PersonaRegistryService(
    private val registro: RegistroPersonas = RegistroPersonas()
) {

    fun registrar(items: List<Resumible>) {
        for (item in items) {
            if (item is Persona) {
                registro.registrar(item)
            }
        }
    }

    fun buscar(nombre: String): Persona? =
        registro.buscar(nombre)
}

/**
 * RESPONSABILIDAD 3:
 * Generar informes
 */
class InformeService(
    private val informe: InformeMarkdown = InformeMarkdown()
) {
    fun generar(titulo: String, items: List<Resumible>): String =
        informe.generar(titulo, items)
}

/**
 * RESPONSABILIDAD 4:
 * Mostrar mensajes (logging / salida)
 */
class ConsoleLogger {
    fun log(mensaje: String) {
        println(mensaje)
    }
}

/**
 * ORQUESTADOR:
 * Solo coordina el flujo, no hace lógica interna
 */
class InformeAppService(
    private val dataProvider: DataProvider = DataProvider(),
    private val registryService: PersonaRegistryService = PersonaRegistryService(),
    private val informeService: InformeService = InformeService(),
    private val logger: ConsoleLogger = ConsoleLogger()
) {

    fun ejecutar() {
        logger.log("[SRP] Preparando datos...")
        val items = dataProvider.obtenerItems()

        logger.log("[SRP] Registrando personas...")
        registryService.registrar(items)

        logger.log("[SRP] Generando informe Markdown...")
        val salida = informeService.generar("Listado", items)

        logger.log("[SRP] Resultado:")
        logger.log(salida)

        logger.log(
            "[SRP] Buscar 'ana' -> ${
                registryService.buscar("ana")?.resumen()
            }"
        )
    }
}

fun main() {
    InformeAppService().ejecutar()
}