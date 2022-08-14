package com.gamefort.data.model.source.remote.common

import com.google.gson.annotations.SerializedName

data class Platform(
    val id: Int,
    val slug: String,
    val name: String,
    @SerializedName("image") val imageUrl: String?,
    @SerializedName("year_start") val yearStart: Int?,
    @SerializedName("year_end") val yearEnd: Int?,
    @SerializedName("games_count") val gamesCount: Int?,
    @SerializedName("image_background") val imageBackgroundUrl: String?
)

