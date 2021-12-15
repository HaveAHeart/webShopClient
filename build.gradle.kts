import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    kotlin("plugin.serialization") version "1.6.10"
    application
}

group = "me.alexey"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.ktor:ktor-client-core:1.6.7")
    implementation("io.ktor:ktor-client-serialization:1.6.7")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:0.20.0")
    implementation("io.ktor:ktor-client-cio:1.6.7")
    implementation("io.ktor:ktor-auth:1.6.7")
    implementation("io.ktor:ktor-auth-jwt:1.6.7")
    implementation("io.ktor:ktor-serialization:1.6.7")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}