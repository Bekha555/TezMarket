package com.example.tezmarket.data.remote.model.AttributesByCategory


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Name(
    @Json(name = "en")
    val en: String? = "",
    @Json(name = "ru")
    val ru: String? = "",
    @Json(name = "tj")
    val tj: String? = ""
)