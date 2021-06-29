import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.20"
    application
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "org.apc.dailyimageqqbot"
version = "1.1"

repositories {
    maven {
        setUrl("https://maven.aliyun.com/nexus/content/groups/public/")
    }
    mavenCentral()
}

dependencies {
    implementation("net.mamoe:mirai-core:2.6.7")
    implementation("com.google.code.gson:gson:2.8.7")
}


tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClassName = "MainKt"
}