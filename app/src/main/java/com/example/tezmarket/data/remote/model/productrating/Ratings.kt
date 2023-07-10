package com.example.tezmarket.data.remote.model.productrating


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ratings(
    @Json(name = "1")
    val x1: Int? = 0,
    @Json(name = "2")
    val x2: Int? = 0,
    @Json(name = "3")
    val x3: Int? = 0,
    @Json(name = "4")
    val x4: Int? = 0,
    @Json(name = "5")
    val x5: Int? = 0
)