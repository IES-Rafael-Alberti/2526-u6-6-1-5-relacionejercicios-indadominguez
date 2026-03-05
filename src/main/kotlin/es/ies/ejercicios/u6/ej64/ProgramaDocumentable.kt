package es.ies.ejercicios.u6.ej64

/** 
* Este fichero contiene ejemplos de:
*- herencia (6.1)
* - clase abstracta e interfaces (6.2)
* - constructores e init en herencia (6.3)
*
* Tu tarea (6.4) es:
* - Entender el código y su relación entre clases e interfaces.
* - Mejorar la documentación KDoc donde sea necesario.
* - Añadir comentarios solo cuando aporten valor.
* - Eliminar comentarios innecesarios o redundantes.
*/

/**
 * Representa un elemento capaz de generar un resumen textual.
 *
 * Cualquier clase que implemente esta interfaz podrá incluirse
 * en una [PlantillaInforme].
 */
interface Resumible {

    /**
     * Devuelve una representación resumida del objeto.
     *
     * @return texto descriptivo del objeto.
     */
    fun resumen(): String
}

/**
 * Implementa el patrón Template Method para la generación de informes.
 *
 * Define la estructura fija de generación:
 * 1. Cabecera
 * 2. Items formateados
 * 3. Pie
 *
 * Las subclases solo pueden personalizar:
 * - [cabecera]
 * - [formatearItem]
 * - [pie]
 *
 * El método [generar] no es `open` para forzar un flujo común.
 */
abstract class PlantillaInforme : Resumible {

    /**
     * Genera el informe completo.
     *
     * @param titulo título del informe.
     * @param items elementos a incluir.
     * @return informe generado en formato texto.
     */
    fun generar(titulo: String, items: List<Resumible>): String {
        val sb = StringBuilder()

        sb.appendLine(cabecera(titulo))

        for (item in items) {
            sb.appendLine(formatearItem(item))
        }

        sb.appendLine(pie())
        return sb.toString()
    }

    /**
     * Genera la cabecera del informe.
     */
    protected open fun cabecera(titulo: String): String = titulo

    /**
     * Define cómo se formatea cada elemento del informe.
     */
    protected abstract fun formatearItem(item: Resumible): String

    /**
     * Genera el pie del informe.
     */
    protected open fun pie(): String = "-- fin --"

    override fun resumen(): String = "PlantillaInforme"
}

/**
 * Implementación de informe en formato Markdown.
 */
class InformeMarkdown : PlantillaInforme() {

    override fun cabecera(titulo: String): String = "# $titulo"

    override fun formatearItem(item: Resumible): String =
        "- ${item.resumen()}"
}

/**
 * Implementación de informe en formato CSV.
 *
 * Se reemplazan comas internas para evitar conflictos con el separador CSV.
 */
class InformeCsv : PlantillaInforme() {

    override fun cabecera(titulo: String): String =
        "titulo,$titulo\nitem"

    override fun formatearItem(item: Resumible): String =
        item.resumen().replace(",", ";")
}

/**
 * Representa una persona básica con nombre y edad.
 *
 * @property nombre nombre de la persona.
 * @property edad edad de la persona.
 */
open class Persona(
    val nombre: String,
    val edad: Int,
) : Resumible {

    init {
        println("[Persona:init] nombre=$nombre edad=$edad")
    }

    /**
     * Constructor secundario que inicializa la persona con edad 0.
     */
    constructor(nombre: String) : this(nombre, edad = 0) {
        println("[Persona:secondary] constructor(nombre)")
    }

    override fun resumen(): String = "$nombre ($edad)"
}

/**
 * Representa un alumno, especialización de [Persona].
 *
 * Añade la propiedad curso.
 */
class Alumno : Persona {

    /**
     * Curso en el que está matriculado el alumno.
     */
    val curso: String

    /**
     * Crea un alumno con nombre, edad y curso.
     */
    constructor(nombre: String, edad: Int, curso: String) :
            super(nombre, edad) {
        this.curso = curso
        println("[Alumno:secondary] nombre=$nombre edad=$edad curso=$curso")
    }

    /**
     * Crea un alumno con edad 0 por defecto.
     */
    constructor(nombre: String, curso: String) :
            this(nombre, edad = 0, curso = curso) {
        println("[Alumno:secondary] constructor(nombre, curso)")
    }

    override fun resumen(): String =
        "Alumno: ${super.resumen()} curso=$curso"
}

/**
 * Gestiona el registro de personas evitando duplicados
 * mediante normalización del nombre.
 *
 * Regla de negocio:
 * Los nombres se almacenan sin espacios extremos y en minúsculas
 * para evitar duplicados por diferencias de formato.
 */
class RegistroPersonas {

    private val personasPorNombre = mutableMapOf<String, Persona>()

    /**
     * Registra una persona en el sistema.
     *
     * Si ya existe una persona con el mismo nombre normalizado,
     * será reemplazada.
     */
    fun registrar(persona: Persona) {
        val clave = normalizarNombre(persona.nombre)
        personasPorNombre[clave] = persona
    }

    /**
     * Busca una persona por nombre aplicando la normalización.
     *
     * @return la persona encontrada o null si no existe.
     */
    fun buscar(nombre: String): Persona? =
        personasPorNombre[normalizarNombre(nombre)]

    /**
     * Normaliza el nombre eliminando espacios externos
     * y convirtiéndolo a minúsculas.
     */
    private fun normalizarNombre(nombre: String): String {
        return nombre.trim().lowercase()
    }
}
