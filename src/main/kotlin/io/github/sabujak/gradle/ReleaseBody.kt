package io.github.sabujak.gradle

import com.google.gson.annotations.SerializedName

data class ReleaseBody(
    @SerializedName("tag_name")
    val tagName: String,
    @SerializedName("target_commitish")
    val targetCommitish: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("body")
    val body: String? = null,
    @SerializedName("draft")
    val draft: Boolean? = null,
    @SerializedName("prerelease")
    val prerelease: Boolean? = null,
    @SerializedName("discussion_category_name")
    val discussionCategoryName: String? = null,
    @SerializedName("generate_release_notes")
    val generateReleaseNotes: Boolean? = null,
)
