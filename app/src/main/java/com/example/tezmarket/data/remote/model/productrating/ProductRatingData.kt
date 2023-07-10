package com.example.tezmarket.data.remote.model.productrating


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductRatingData(
    @Json(name = "ratingAvg")
    val ratingAvg: Double? = 0.0,
    @Json(name = "ratingCount")
    val ratingCount: Int? = 0,
    @Json(name = "ratings")
    val ratings: Ratings? = Ratings(),
    @Json(name = "reviews")
    val reviews: Reviews? = Reviews()
)