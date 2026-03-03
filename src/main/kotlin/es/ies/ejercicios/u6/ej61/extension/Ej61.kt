package es.ies.ejercicios.u6.ej61.extension

/**
 * Ejercicio 6.1 — Tipos de herencia, clases y subclases (RA7.a).
 *
 *
 * Recomendación: crea subpaquetes (p. ej. `especializacion`, `extension`, etc.)
 * y añade un `main` de ejemplo que muestre polimorfismo.
 */

open class Dispositivo(
    val marca: String
) {

    open fun encender() {
        println("El dispositivo $marca se está encendiendo")
    }

    open fun apagar() {
        println("El dispositivo $marca se está apagando")
    }
}

class Impresora(marca: String) : Dispositivo(marca) {

    fun imprimir(documento: String) {
        println("Imprimiendo documento: $documento")
    }
}

class Escaner(marca: String) : Dispositivo(marca) {

    fun escanear() {
        println("Escaneando documento...")
    }
}

