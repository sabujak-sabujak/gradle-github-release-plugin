package io.github.sabujak.gradle

open class GithubReleasePluginExtension(
    var token: String? = null,
    var owner: String? = null,
    var repo: String? = null,
    var tagName: String? = null,
    var targetCommitish: String? = null,
    var name: String? = null,
    var body: String? = null,
    var draft: Boolean? = null,
    var prerelease: Boolean? = null,
    var discussionCategoryName: String? = null,
    var generateReleaseNotes: Boolean? = null
)