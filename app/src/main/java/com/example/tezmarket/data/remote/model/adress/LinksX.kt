package com.example.tezmarket.data.remote.model.adress


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinksX(
    @Json(name = "first")
    val first: String? = "",
    @Json(name = "last")
    val last: String? = "",
    @Json(name = "next")
    val next: Any? = Any(),
    @Json(name = "prev")
    val prev: Any? = Any()
)