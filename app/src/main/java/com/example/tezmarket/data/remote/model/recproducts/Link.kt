package com.example.tezmarket.data.remote.model.recproducts


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Link(
    @Json(name = "active")
    val active: Boolean? = false,
    @Json(name = "label")
    val label: String? = "",
    @Json(name = "url")
    val url: String? = ""
)