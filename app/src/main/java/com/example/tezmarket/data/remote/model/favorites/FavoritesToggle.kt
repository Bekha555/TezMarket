package com.example.tezmarket.data.remote.model.favorites

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FavoritesToggle(
    @Json(name = "message")
    val message: String
)
