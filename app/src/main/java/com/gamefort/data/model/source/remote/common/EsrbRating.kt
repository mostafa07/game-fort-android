package com.gamefort.data.model.source.remote.common

// TODO create enums for name and slug
// slug string Enum: "everyone" "everyone-10-plus" "teen" "mature" "adults-only" "rating-pending"
//name string Enum: "Everyone" "Everyone 10+" "Teen" "Mature" "Adults Only" "Rating Pending"

data class EsrbRating(
    val id: Int,
    val slug: String,
    val name: String
)