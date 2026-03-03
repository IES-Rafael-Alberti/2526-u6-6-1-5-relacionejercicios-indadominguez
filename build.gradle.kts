plugins {
    kotlin("jvm") version "1.9.24"
    application
    id("org.jetbrains.dokka") version "1.9.20"
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}

tasks.dokkaHtml {
    outputDirectory.set(file("Doc"))
}
