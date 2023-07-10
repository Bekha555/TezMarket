package com.example.tezmarket.data.remote.model.productrating


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Reviews(
    @Json(name = "data")
    val `data`: List<DataX>? = listOf(),
    @Json(name = "links")
    val links: Links? = Links(),
    @Json(name = "meta")
    val meta: Meta? = Meta()
)