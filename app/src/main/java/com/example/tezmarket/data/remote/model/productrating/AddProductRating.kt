package com.example.tezmarket.data.remote.model.productrating


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddProductRating(
    @Json(name = "data")
    val `data`: Data? = Data()
)