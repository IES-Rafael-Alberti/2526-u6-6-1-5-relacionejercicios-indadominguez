package es.ies.ejercicios.u6.ej65.isp

import es.ies.ejercicios.u6.ej64.Persona

/**
 * ISP - Versión refactorizada
 *
 * En v0 existía una interfaz "gorda" que obligaba a implementar:
 * - guardar
 * - buscar
 * - exportarCsv
 * - borrarTodo
 *
 * Ahora segregamos por capacidades.
 */

/* ================================
   INTERFACES SEGREGADAS
   ================================ */

/**
 * Capacidad de lectura
 */
interface RepositorioLectura {
    fun buscar(nombre: String): Persona?
}

/**
 * Capacidad de escritura
 */
interface RepositorioEscritura {
    fun guardar(persona: Persona)
}

/**
 * Capacidad de borrado
 */
interface RepositorioBorrado {
    fun borrarTodo()
}

/**
 * Capacidad de exportación
 */
interface RepositorioExportacion {
    fun exportarCsv(): String
}


/* ================================
   IMPLEMENTACIÓN COMPLETA
   ================================ */

class RepositorioMemoria :
    RepositorioLectura,
    RepositorioEscritura,
    RepositorioBorrado,
    RepositorioExportacion {

    private val map = mutableMapOf<String, Persona>()

    override fun guardar(persona: Persona) {
        map[persona.nombre] = persona
    }

    override fun buscar(nombre: String): Persona? =
        map[nombre]

    override fun borrarTodo() {
        map.clear()
    }

    override fun exportarCsv(): String =
        buildString {
            appendLine("nombre,edad")
            for (persona in map.values) {
                appendLine("${persona.nombre},${persona.edad}")
            }
        }
}


/* ================================
   CLIENTES
   ================================ */

/**
 * Cliente que solo necesita buscar
 */
class ClienteConsulta(
    private val repo: RepositorioLectura
) {
    fun ejecutar(nombre: String) {
        println("Buscar $nombre -> ${repo.buscar(nombre)?.resumen()}")
    }
}

/**
 * Cliente que solo necesita guardar
 */
class ClienteAlta(
    private val repo: RepositorioEscritura
) {
    fun ejecutar(persona: Persona) {
        repo.guardar(persona)
        println("Persona guardada: ${persona.resumen()}")
    }
}

/**
 * Cliente administrador (usa todo)
 */
class ClienteAdmin(
    private val repoBorrado: RepositorioBorrado,
    private val repoExportacion: RepositorioExportacion
) {
    fun limpiar() {
        repoBorrado.borrarTodo()
        println("Repositorio limpiado")
    }

    fun exportar() {
        println(repoExportacion.exportarCsv())
    }
}


/* ================================
   MAIN / DEMO
   ================================ */

fun main() {

    val repo = RepositorioMemoria()

    val clienteAlta = ClienteAlta(repo)
    val clienteConsulta = ClienteConsulta(repo)
    val clienteAdmin = ClienteAdmin(repo, repo)

    clienteAlta.ejecutar(Persona("Ana", 20))
    clienteConsulta.ejecutar("Ana")

    clienteAdmin.exportar()
    clienteAdmin.limpiar()
}