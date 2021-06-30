import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.20"
}

group = "org.apc.dailyimageqqbot"
version = "1.0"



tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

dependencies {
    api("net.mamoe", "mirai-core-api", "2.6.7")
    api("com.google.code.gson:gson:2.8.7")
}