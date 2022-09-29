package cl.orlandoormazabal.reigndemoapp.data.model

import com.google.gson.annotations.SerializedName

data class HitList(
    @SerializedName ("hits")
    val hitList: List<Hit>)

data class Hit(
    @SerializedName("objectID")
    val objectId: String,
    @SerializedName ("created_at")
    val createdAt: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("story_url")
    val storyUrl: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("_highlightResult")
    val highLightResult: HighLightResult)

data class HighLightResult(
    @SerializedName("story_title")
    val storyTitle: StoryTitle?,
    @SerializedName("title")
    val title: Title,
    @SerializedName("story_url")
    val storyUrl: StoryUrl?,
    @SerializedName("url")
    val url: Url?
)

data class Title(
    @SerializedName("value")
    val value: String)

data class StoryTitle(
    @SerializedName("value")
    val value: String)

data class StoryUrl(
    @SerializedName("value")
    val value: String?)

data class Url(
    @SerializedName("value")
    val value: String?)