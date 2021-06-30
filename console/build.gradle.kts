import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.20"
    application
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "org.apc.dailyimageqqbot.console"
version = "1.2"

dependencies {
    implementation(project(":common"))
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

dependencies {
    runtimeOnly("net.mamoe", "mirai-core", "2.6.7")
}

application {
    mainClassName = "MainKt"
}