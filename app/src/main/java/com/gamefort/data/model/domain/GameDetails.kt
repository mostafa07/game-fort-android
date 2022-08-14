package com.gamefort.data.model.domain

data class GameDetails(
    val id: Int,
    val name: String,
    val descriptionRaw: String?,
    val released: String?,
    val backgroundImageUrl: String?,
    val rating: Number,
)
