package es.ies.ejercicios.u6.ej65.lsp

import es.ies.ejercicios.u6.ej64.Persona

/**
 * LSP - Versión refactorizada
 *
 * En lugar de tener una clase base que obliga a implementar
 * guardar y buscar, separamos capacidades:
 *
 * - Solo lectura
 * - Lectura + escritura
 *
 * Así ninguna subclase rompe el contrato.
 */

/* ================================
   INTERFACES (CAPACIDADES)
   ================================ */

/**
 * Capacidad de solo lectura
 */
interface RepositorioLecturaPersonas {
    fun buscar(nombre: String): Persona?
}

/**
 * Capacidad de escritura
 */
interface RepositorioEscrituraPersonas {
    fun guardar(persona: Persona)
}

/**
 * Repositorio completo (lectura + escritura)
 */
interface RepositorioPersonas :
    RepositorioLecturaPersonas,
    RepositorioEscrituraPersonas


/* ================================
   IMPLEMENTACIONES
   ================================ */

/**
 * Implementación completa (lectura + escritura)
 */
class RepositorioMemoria : RepositorioPersonas {

    private val map = mutableMapOf<String, Persona>()

    override fun guardar(persona: Persona) {
        map[persona.nombre] = persona
    }

    override fun buscar(nombre: String): Persona? =
        map[nombre]
}

/**
 * Implementación solo lectura.
 * NO implementa escritura.
 */
class RepositorioSoloLectura(
    private val origen: RepositorioLecturaPersonas
) : RepositorioLecturaPersonas {

    override fun buscar(nombre: String): Persona? =
        origen.buscar(nombre)
}


/* ================================
   CLIENTES
   ================================ */

/**
 * Cliente que necesita escribir
 */
fun clienteEscritura(repo: RepositorioPersonas) {
    repo.guardar(Persona("Ana", 20))
    println("Buscar Ana -> ${repo.buscar("Ana")?.resumen()}")
}

/**
 * Cliente que solo necesita leer
 */
fun clienteLectura(repo: RepositorioLecturaPersonas) {
    println("Buscar Ana -> ${repo.buscar("Ana")?.resumen()}")
}


/* ================================
   MAIN / DEMO
   ================================ */

fun main() {

    println("[LSP] Repositorio completo")
    val repoCompleto = RepositorioMemoria()
    clienteEscritura(repoCompleto)

    println("\n[LSP] Repositorio solo lectura")
    val repoSoloLectura = RepositorioSoloLectura(repoCompleto)
    clienteLectura(repoSoloLectura)

    // Esto ya no compila (y eso es bueno):
    // clienteEscritura(repoSoloLectura)
}