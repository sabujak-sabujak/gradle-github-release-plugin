package io.github.sabujak.gradle

import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class ReleaseTask : DefaultTask() {

    init {
        group = "github release"
        description = "create github release"
    }

    @TaskAction
    fun createGithubRelease() {
        val githubReleasePluginExtension =
            project.extensions.getByType(GithubReleasePluginExtension::class.java)

        logging(githubReleasePluginExtension)

        createRelease(githubReleasePluginExtension)
    }

    private fun createRelease(githubReleasePluginExtension: GithubReleasePluginExtension) {
        val token = githubReleasePluginExtension.token ?: run {
            throw IllegalArgumentException("token is null")
        }

        getApiUrl(githubReleasePluginExtension)
            .httpPost()
            .authentication()
            .bearer(token)
            .appendHeader("Accept" to ACCEPT)
            .jsonBody(getJsonBody(githubReleasePluginExtension))
            .response { request, response, result ->
                result.fold({
                    println("success ::: ${response.body().asString("application/json")}")
                }, {
                    println("Error ::: $it")
                })
            }.get()
    }

    private fun getApiUrl(githubReleasePluginExtension: GithubReleasePluginExtension): String {
        val owner = githubReleasePluginExtension.owner ?: run {
            throw IllegalArgumentException("owner is null")
        }

        val repo = githubReleasePluginExtension.repo ?: run {
            throw IllegalArgumentException("repo is null")
        }


        return "$BASE_URL/$owner/$repo/releases"
    }

    private fun getJsonBody(githubReleasePluginExtension: GithubReleasePluginExtension): String {
        val tagName = githubReleasePluginExtension.tagName ?: run {
            throw IllegalArgumentException("tagName is null")
        }

        val releaseBody = githubReleasePluginExtension.run {
            ReleaseBody(
                tagName,
                targetCommitish,
                name,
                body,
                draft,
                prerelease,
                discussionCategoryName,
                generateReleaseNotes
            )
        }

        return Gson().toJson(releaseBody)
    }

    private fun logging(githubReleasePluginExtension: GithubReleasePluginExtension) {
        val logMessage = "POST ${getApiUrl(githubReleasePluginExtension)}\n" +
                "> Authorization: (not shown)\n" +
                "> Accept: ${ACCEPT}\n" +
                "> body: ${getJsonBody(githubReleasePluginExtension)}\n"

        println(logMessage)
    }

    companion object {
        const val BASE_URL = "https://api.github.com/repos"
        const val ACCEPT = "application/vnd.github.v3+json"
    }
}

