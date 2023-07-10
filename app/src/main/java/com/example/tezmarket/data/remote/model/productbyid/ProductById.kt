package com.example.tezmarket.data.remote.model.productbyid


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductById(
    @Json(name = "data")
    val `data`: Data? = Data()
)