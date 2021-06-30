import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.20"
    kotlin("plugin.serialization") version "1.5.20"
    id("net.mamoe.mirai-console") version "2.6.7"
}

group = "org.apc.dailyimageqqbot.plugin"
version = "1.2"

dependencies {
    implementation(project(":common"))
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}