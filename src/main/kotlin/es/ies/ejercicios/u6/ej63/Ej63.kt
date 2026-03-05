package es.ies.ejercicios.u6.ej63

/**
 * Ejercicio 6.3 — Incidencia de constructores en la herencia (RA7.c).
 *
 * Punto de partida: revisa `Figuras.kt` y completa lo indicado en `docs/ejercicios/6.3.md`.
 */
object Ej63

fun main() {
    println("=== Creando Rectangulo con primario ===")
    val r1 = Rectangulo("rojo", "mi-rectangulo", 10, 20)

    println("\n=== Creando Rectangulo con secundario (ancho, alto) ===")
    val r2 = Rectangulo(5, 15)

    println("\n=== Creando Rectangulo cuadrado ===")
    val r3 = Rectangulo(7)

    println("\n=== Creando Circulo con primario ===")
    val c1 = Circulo("azul", "mi-circulo", 12)

    println("\n=== Creando Circulo con secundario ===")
    val c2 = Circulo(8)

    println("\n=== Creando Triangulo con base/altura ===")
    val t1 = Triangulo(6, 9)

    println("\n=== Creando Triangulo cuadrado ===")
    val t2 = Triangulo(4)
}
