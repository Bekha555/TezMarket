package com.example.tezmarket.data.remote.model.modcart


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ModCartProduct(
    @Json(name = "data")
    val `data`: Data? = Data()
)