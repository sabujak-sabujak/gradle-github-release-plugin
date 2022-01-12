group = "io.github.sabujak-sabujak"
version = "0.0.2"

buildscript {
    repositories {
        mavenLocal()
    }

    dependencies {
        classpath("io.github.sabujak-sabujak:gradle-github-release-plugin:0.0.2")
    }
}

apply {
    plugin("io.github.sabujak-sabujak")
}

plugins {
    kotlin("jvm") version "1.6.10"
    `kotlin-dsl`
    `maven-publish`
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "0.19.0"
    id("signing")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.github.kittinunf.fuel:fuel:2.3.1")
    implementation("com.google.code.gson:gson:2.8.9")
}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
    from("LICENCE.md") {
        into("META-INF")
    }
}

val artifactName = project.name
val artifactGroup = project.group.toString()
val artifactVersion = project.version.toString()

gradlePlugin {
    plugins {
        create("githubReleasePlugin") {
            id = artifactGroup
            displayName = "Github Release"
            description = "A plugin to create a release in a github repo"
            implementationClass = "io.github.sabujak.gradle.GithubReleasePlugin"
        }
    }
}

pluginBundle {
    website = "https://github.com/sabujak-sabujak/gradle-github-release-plugin"
    vcsUrl = "https://github.com/sabujak-sabujak/gradle-github-release-plugin"
    tags = listOf("github release")
}