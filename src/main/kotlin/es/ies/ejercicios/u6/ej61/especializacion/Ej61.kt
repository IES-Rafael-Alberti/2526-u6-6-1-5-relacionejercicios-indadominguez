package es.ies.ejercicios.u6.ej61.especializacion

/**
 * Ejercicio 6.1 — Tipos de herencia, clases y subclases (RA7.a).
 *
 *
 * Recomendación: crea subpaquetes (p. ej. `especializacion`, `extension`, etc.)
 * y añade un `main` de ejemplo que muestre polimorfismo.
 */

open class Empleado(
    val nombre: String,
    protected val salarioBase: Double
) {

    open fun calcularSalario(): Double {
        println("Calculando salario base de $nombre")
        return salarioBase
    }

    open fun trabajar() {
        println("$nombre está trabajando como empleado genérico")
    }
}

class Programador(
    nombre: String,
    salarioBase: Double,
    private val lineasCodigo: Int
) : Empleado(nombre, salarioBase) {

    override fun calcularSalario(): Double {
        val bonus = lineasCodigo * 0.5
        println("Calculando salario de programador con bonus por líneas de código")
        return salarioBase + bonus
    }

    override fun trabajar() {
        println("$nombre está programando $lineasCodigo líneas de código")
    }
}

class Diseñador(
    nombre: String,
    salarioBase: Double,
    private val numeroDisenos: Int
) : Empleado(nombre, salarioBase) {

    override fun calcularSalario(): Double {
        val bonus = numeroDisenos * 10
        println("Calculando salario de diseñador con bonus por diseños realizados")
        return salarioBase + bonus
    }

    override fun trabajar() {
        println("$nombre está creando $numeroDisenos diseños")
    }
}
