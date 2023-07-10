package com.example.tezmarket.data.remote.model.prodouctsbycategory


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Links(
    @Json(name = "first")
    val first: String? = "",
    @Json(name = "last")
    val last: String? = "",
    @Json(name = "next")
    val next: String? = "",
    @Json(name = "prev")
    val prev: Any? = Any()
)