plugins {
    id("java")
    id("com.gradleup.shadow") version "9.3.1"
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

tasks.compileJava {
    options.release.set(21)
}

group = "net.thenextlvl.namemc"
version = "1.0.3"

repositories {
    mavenCentral()
    maven("https://repo.thenextlvl.net/releases")
    maven("https://repo.thenextlvl.net/snapshots")
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")

    implementation("net.thenextlvl.core:files:4.0.0-pre1")
    implementation("net.thenextlvl:i18n:1.2.0")

    annotationProcessor("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
}

tasks.shadowJar {
    minimize()
}