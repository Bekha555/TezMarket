package com.example.tezmarket.data.remote.model.addcart


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddCartProduct(
    @Json(name = "data")
    val `data`: Data? = Data()
)