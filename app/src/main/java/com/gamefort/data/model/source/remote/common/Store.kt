package com.gamefort.data.model.source.remote.common

import com.google.gson.annotations.SerializedName

data class Store(
    val id: Int?,
    val name: String?,
    val slug: String?,
    val domain: String?,
    @SerializedName("games_count") val gamesCount: Int?,
    @SerializedName("image_background") val imageBackgroundUrl: String?
)

