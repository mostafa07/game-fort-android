package com.gamefort.data.model.source.remote.gamedetails

import com.gamefort.data.model.domain.GameDetails
import com.gamefort.data.model.source.remote.common.*
import com.google.gson.annotations.SerializedName

data class GameDetailsApiResponse(
    val id: Int,
    val slug: String,
    val name: String,
    @SerializedName("name_original") val nameOriginal: String,
    val description: String,
    @SerializedName("description_raw") val descriptionRaw: String?,
    val metacritic: Int?,
    @SerializedName("metacritic_platforms") val metacriticPlatforms: List<MetacriticPlatforms>?,
    val released: String?,
    val tba: Boolean?,
    val updated: String?,
    @SerializedName("background_image") val backgroundImageUrl: String?,
    @SerializedName("background_image_additional") val backgroundImageAdditionalUrl: String?,
    val website: String,
    val rating: Number,
    @SerializedName("rating_top") val ratingTop: Number?,
    val ratings: List<Rating>?,
    val reactions: Reactions?,
    val added: Number?,
    @SerializedName("added_by_status") val addedByStatus: AddedByStatus?,
    @SerializedName("playtime") val playtimeInHours: Int?,
    @SerializedName("screenshots_count") val screenshotsCount: Int?,
    @SerializedName("movies_count") val moviesCount: Int?,
    @SerializedName("creators_count") val creatorsCount: Int?,
    @SerializedName("achievements_count") val achievementsCount: Int?,
    @SerializedName("parent_achievements_count") val parentAchievementsCount: String?,
    @SerializedName("reddit_url") val redditUrl: String,
    @SerializedName("reddit_name") val redditName: String,
    @SerializedName("reddit_description") val redditDescription: String,
    @SerializedName("reddit_logo") val redditLogo: String,
    @SerializedName("reddit_count") val redditCount: Int?,
    @SerializedName("twitch_count") val twitchCount: String?,
    @SerializedName("youtube_count") val youtubeCount: String?,
    @SerializedName("reviews_count") val reviewsCount: Int?,
    @SerializedName("reviews_text_count") val reviewsTextCount: String?,
    @SerializedName("ratings_count") val ratingsCount: Int?,
    @SerializedName("suggestions_count") val suggestionsCount: Int?,
    @SerializedName("alternative_names") val alternativeNames: List<String>?,
    @SerializedName("metacritic_url") val metacriticUrl: String,
    @SerializedName("parents_count") val parentsCount: Int?,
    @SerializedName("additions_count") val additionsCount: Int?,
    @SerializedName("game_series_count") val gameSeriesCount: Int?,
    @SerializedName("esrb_rating") val esrbRating: EsrbRating?,
    val platforms: List<Platforms>?,
    @SerializedName("user_game") val userGame: Any?,
    @SerializedName("saturated_color") val saturatedColor: String?,
    @SerializedName("dominant_color") val dominantColor: String?,
    @SerializedName("parent_platforms") val parentPlatforms: List<ParentPlatforms>?,
    val stores: List<Stores>?,
    val developers: List<Developers>?,
    val genres: List<Genres>?,
    val tags: List<Tags>?,
    val publishers: List<Publishers>?,
    val clip: Any?,
)


fun GameDetailsApiResponse.toDomainModel(): GameDetails {
    return GameDetails(
        id = id,
        name = name,
        descriptionRaw = descriptionRaw,
        released = released,
        backgroundImageUrl = backgroundImageUrl,
        rating = rating,
    )
}