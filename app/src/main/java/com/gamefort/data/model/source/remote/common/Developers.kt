package com.gamefort.data.model.source.remote.common

import com.google.gson.annotations.SerializedName

data class Developers(
    val id: Int?,
    val name: String?,
    val slug: String?,
    @SerializedName("games_count") val gamesCount: Int?,
    @SerializedName("image_background") val imageBackgroundUrl: String?
)