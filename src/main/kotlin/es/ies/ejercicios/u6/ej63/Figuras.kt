package es.ies.ejercicios.u6.ej63

/**
 * Ejercicio 6.3: Constructores primarios y secundarios, delegación y herencia.
 */

open class Figura(
    val color: String,
    val etiqueta: String,
) {
    init {
        println("[Figura:init] color=$color etiqueta=$etiqueta")
    }

    constructor(color: String) : this(color, etiqueta = "sin-etiqueta") {
        println("[Figura:secondary] constructor(color)")
    }
}

class Rectangulo(
    color: String,
    etiqueta: String,
    val ancho: Int,
    val alto: Int,
) : Figura(color, etiqueta) {
    init {
        println("[Rectangulo:init] ancho=$ancho alto=$alto")
    }

    constructor(ancho: Int, alto: Int) : this(
        color = "sin-color",
        etiqueta = "rectangulo",
        ancho = ancho,
        alto = alto,
    ) {
        println("[Rectangulo:secondary] constructor(ancho, alto)")
    }

    // Constructor secundario que crea un cuadrado
    constructor(lado: Int) : this(ancho = lado, alto = lado) {
        println("[Rectangulo:secondary] constructor(cuadrado)")
    }
}

class Circulo(
    color: String,
    etiqueta: String,
    val radio: Int,
) : Figura(color, etiqueta) {
    init {
        println("[Circulo:init] radio=$radio")
    }

    // Constructor secundario que delega en el primario
    constructor(radio: Int) : this(color = "sin-color", etiqueta = "circulo", radio = radio) {
        println("[Circulo:secondary] constructor(radio)")
    }
}

// Subclase sin constructor primario
class Triangulo : Figura {
    val base: Int
    val altura: Int

    constructor(base: Int, altura: Int) : super("sin-color", "triangulo") {
        println("[Triangulo:secondary] constructor(base, altura) -> constructor padre")
        this.base = base
        this.altura = altura
    }

    constructor(lado: Int) : this(base = lado, altura = lado) {
        println("[Triangulo:secondary] constructor(lado) -> this(base, altura)")
    }
}