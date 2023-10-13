plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "net.thenextlvl"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.thenextlvl.net/releases")
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.26")
    compileOnly("net.thenextlvl.core:annotations:1.0.0")
    compileOnly("com.velocitypowered:velocity-api:3.1.1")

    implementation("net.thenextlvl.core:i18n:1.0.8")
    implementation("net.thenextlvl.core:api:4.0.2")

    annotationProcessor("org.projectlombok:lombok:1.18.26")
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }
    shadowJar {
        minimize()
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}