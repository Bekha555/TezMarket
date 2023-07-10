package com.example.tezmarket.data.remote.model.simularproducts


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SimularProduct(
    @Json(name = "data")
    val `data`: List<Data>? = listOf(),
    @Json(name = "links")
    val links: Links? = Links(),
    @Json(name = "meta")
    val meta: Meta? = Meta()
)