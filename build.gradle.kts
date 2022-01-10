import org.jetbrains.kotlin.konan.properties.Properties

group = "io.github.sabujak-sabujak"
version = "0.0.1"

buildscript {
    repositories {
        mavenLocal()
    }

    dependencies {
        classpath("io.github.sabujak-sabujak:gradle-github-release-plugin:0.0.1")
    }
}

plugins {
    kotlin("jvm") version "1.6.10"
    `kotlin-dsl`
    `maven-publish`
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "0.19.0"
    id("signing")
}

apply {
    plugin("io.github.sabujak-sabujak")
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

val pomUrl = "https://github.com/sabujak-sabujak/gradle-github-release-plugin"
val pomScmUrl = "https://github.com/sabujak-sabujak/gradle-github-release-plugin"
val pomIssueUrl = "https://github.com/sabujak-sabujak/gradle-github-release-plugin/issues"
val pomDesc = "https://github.com/sabujak-sabujak/gradle-github-release-plugin"

val githubRepository = "sabujak-sabujak/gradle-github-release-plugin"
val githubReadme = "README.md"

val pomLicenseName = "The Apache Software License, Version 2.0"
val pomLicenseUrl = "http://www.apache.org/licenses/LICENSE-2.0.txt"
val pomLicenseDist = "repo"

val pomDeveloperId = "sabujak-sabujak"
val pomDeveloperName = "sabujak"

val pluginDescription = "github Release plugin"

ext["signing.keyId"] = ""
ext["signing.password"] = ""
ext["signing.key"] = ""
ext["ossrhUsername"] = ""
ext["ossrhPassword"] = ""

fun getExtraString(name: String) = ext[name]?.toString()

afterEvaluate {
    val secretPropsFile = project.rootProject.file("local.properties")
    if (secretPropsFile.exists()) {
        secretPropsFile.reader().use {
            Properties().apply {
                load(it)
            }
        }.onEach { (name, value) ->
            ext[name.toString()] = value
        }
    } else {
        // Use system environment variables
        ext["ossrhUsername"] = System.getenv("OSSRH_USERNAME")
        ext["ossrhPassword"] = System.getenv("OSSRH_PASSWORD")
        ext["signing.keyId"] = System.getenv("SIGNING_KEY_ID")
        ext["signing.password"] = System.getenv("SIGNING_PASSWORD")
        ext["signing.key"] = System.getenv("SIGNING_KEY")
    }

    publishing {
        publications {
            create<MavenPublication>("gradle-github-release-plugin") {
                groupId = artifactGroup
                artifactId = artifactName
                version = artifactVersion
                artifact(sourcesJar)
                from(components["java"])

                pom.withXml {
                    asNode().apply {
                        appendNode("description", pomDesc)
                        appendNode("name", rootProject.name)
                        appendNode("url", pomUrl)
                        appendNode("licenses").appendNode("license").apply {
                            appendNode("name", pomLicenseName)
                            appendNode("url", pomLicenseUrl)
                            appendNode("distribution", pomLicenseDist)
                        }
                        appendNode("developers").appendNode("developer").apply {
                            appendNode("id", pomDeveloperId)
                            appendNode("name", pomDeveloperName)
                        }
                        appendNode("scm").apply {
                            appendNode("url", pomScmUrl)
                        }
                    }
                }
            }
        }

        repositories {
            maven {
                setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = getExtraString("ossrhUsername")
                    password = getExtraString("ossrhPassword")
                }
            }
        }
    }

    signing {
        useInMemoryPgpKeys(
            getExtraString("signing.keyId"),
            getExtraString("signing.key"),
            getExtraString("signing.password"),
        )
        sign(publishing.publications)
    }
}

pluginBundle {
    website = "https://github.com/sabujak-sabujak/gradle-github-release-plugin"
    vcsUrl = "https://github.com/sabujak-sabujak/gradle-github-release-plugin"
    tags = listOf("github release")
}