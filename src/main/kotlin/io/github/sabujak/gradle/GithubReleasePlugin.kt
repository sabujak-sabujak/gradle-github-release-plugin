package io.github.sabujak.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class GithubReleasePlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = target.run {
        extensions.create("githubRelease", GithubReleasePluginExtension::class.java)
        tasks.register("createGithubRelease", ReleaseTask::class.java)
    }
}