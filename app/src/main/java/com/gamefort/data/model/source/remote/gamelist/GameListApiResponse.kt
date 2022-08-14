package com.gamefort.data.model.source.remote.gamelist

import com.gamefort.data.model.domain.GameListItem
import com.gamefort.data.model.source.remote.common.*
import com.google.gson.annotations.SerializedName

data class GameListApiResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Result>,
    @SerializedName("seo_title") val seoTitle: String?,
    @SerializedName("seo_description") val seoDescription: String?,
    @SerializedName("seo_keywords") val seoKeywords: String?,
    @SerializedName("seo_h1") val seoH1: String?,
    @SerializedName("noindex") val noIndex: Boolean?,
    @SerializedName("nofollow") val noFollow: Boolean?,
    val description: String?,
    val filters: Filters?,
    @SerializedName("nofollow_collections") val noFollowCollections: List<String>?
)

data class Result(
    val id: Int,
    val slug: String,
    val name: String,
    val released: String?,
    val tba: Boolean?,
    @SerializedName("background_image") val backgroundImageUrl: String?,
    val rating: Number?,
    @SerializedName("rating_top") val ratingTop: Int?,
    val ratings: List<Rating>?,
    @SerializedName("ratings_count") val ratingsCount: Number?,
    @SerializedName("reviews_text_count") val reviewsTextCount: String?,
    val added: Int?,
    @SerializedName("added_by_status") val addedByStatus: AddedByStatus?,
    val metacritic: Int?,
    @SerializedName("playtime") val playtimeInHours: Int?,
    @SerializedName("suggestions_count") val suggestionsCount: Number?,
    val updated: String?,
    @SerializedName("esrb_rating") val esrbRating: EsrbRating?,
    val platforms: List<Platforms>?
)


fun GameListApiResponse.toDomainModel(): List<GameListItem> {
    return results.map {
        GameListItem(
            id = it.id,
            name = it.name,
            backgroundImageUrl = it.backgroundImageUrl,
        )
    }
}