package es.ies.ejercicios.u6.ej61


import es.ies.ejercicios.u6.ej61.especializacion.*
import es.ies.ejercicios.u6.ej61.extension.*

fun main() {

    println("===== ESPECIALIZACIÓN =====")

    val empleado1: Empleado = Programador("Indalecio", 2000.0, 500)
    val empleado2: Empleado = Diseñador("Oscar", 1800.0, 20)

    empleado1.trabajar()
    println("Salario final: ${empleado1.calcularSalario()}")

    println()

    empleado2.trabajar()
    println("Salario final: ${empleado2.calcularSalario()}")

    println("\n===== EXTENSIÓN =====")

    val dispositivo1: Dispositivo = Impresora("HP")
    val dispositivo2: Dispositivo = Escaner("Canon")

    dispositivo1.encender()
    dispositivo2.encender()

    dispositivo1.apagar()
    dispositivo2.apagar()
}