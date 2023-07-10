package com.example.tezmarket.data.remote.model.productrating


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataX(
    @Json(name = "client")
    val client: ClientXX? = ClientXX(),
    @Json(name = "comment")
    val comment: String? = "",
    @Json(name = "created_at")
    val createdAt: String? = "",
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "rating")
    val rating: Int? = 0
)