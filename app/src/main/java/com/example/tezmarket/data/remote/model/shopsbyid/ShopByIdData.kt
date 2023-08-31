package com.example.tezmarket.data.remote.model.shopsbyid


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShopByIdData(
    @Json(name = "data")
    val `data`: Data = Data()
)