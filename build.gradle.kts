plugins {
    id("java")
    id("io.github.goooler.shadow") version "8.1.8"
}

java {
    targetCompatibility = JavaVersion.VERSION_21
    sourceCompatibility = JavaVersion.VERSION_21
}

group = "net.thenextlvl.namemc"
version = "1.0.3"

repositories {
    mavenCentral()
    maven("https://repo.thenextlvl.net/releases")
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.28")
    compileOnly("com.velocitypowered:velocity-api:3.2.0-SNAPSHOT")

    implementation("net.thenextlvl.core:i18n:1.0.8")
    implementation("net.thenextlvl.core:api:4.0.2")

    annotationProcessor("org.projectlombok:lombok:1.18.28")
    annotationProcessor("com.velocitypowered:velocity-api:3.2.0-SNAPSHOT")
}

tasks.shadowJar {
    minimize()
}