/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

plugins {
    application
    eclipse
    java
    alias(libs.plugins.java.qa)
}

group = "io.github.smartoperatingblock"

repositories {
    mavenCentral()
    maven {
        url = uri("https://raw.githubusercontent.com/jacamo-lang/mvn-repo/master")
    }
    maven {
        url = uri("https://repo.gradle.org/gradle/libs-releases")
    }
    maven {
        url = uri("https://packages.confluent.io/maven")
    }
    maven {
        url = uri("https://git.informatik.uni-hamburg.de/api/v4/groups/sane-public/-/packages/maven")
    }
}

dependencies {
    implementation(libs.azure.digital.twins)
    implementation(libs.azure.identity)
    implementation(libs.gson)
    implementation(libs.jacamo)
    implementation(libs.kafka.clients)
    implementation(libs.kafka.json)
    implementation(libs.snakeyaml)
    implementation(libs.wot.servient)
    implementation(libs.wot.servient.binding.http)
    testImplementation(libs.junit.api)
    testRuntimeOnly(libs.junit.engine)
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
        showCauses = true
        showStackTraces = true
        events(*org.gradle.api.tasks.testing.logging.TestLogEvent.values())
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}

sourceSets.getByName("main") {
    java.srcDir("src/env")
    java.srcDir("src/agt")
    java.srcDir("src/org")
    java.srcDir("src/int")
    java.srcDir("src/java")
    resources.srcDir("src/resources")
}

tasks.named<JavaExec>("run") {
    dependsOn("classes")
    group = "JaCaMo"
    description = "runs the JaCaMo application"
    doFirst {
        mkdir("log")
    }
    mainClass.set("jacamo.infra.JaCaMoLauncher")
    args("automation_management_microservice.jcm")
    classpath = project.sourceSets.main.get().runtimeClasspath
}

tasks.named("clean") {
    delete("bin")
    buildDir.deleteRecursively()
    delete("log")
}
