package com.example.tezmarket.data.remote.model.modcart


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "created_at")
    val createdAt: String? = "",
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "price")
    val price: Int? = 0,
    @Json(name = "product")
    val product: Product? = Product(),
    @Json(name = "quantity")
    val quantity: String? = ""
)