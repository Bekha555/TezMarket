package com.example.tezmarket.data.remote.model.myadvertisements


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "avgRating")
    val avgRating: Int? = 0,
    @Json(name = "category")
    val category: Category? = Category(),
    @Json(name = "created_at")
    val createdAt: String? = "",
    @Json(name = "desc")
    val desc: String? = "",
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "image")
    val image: String? = "",
    @Json(name = "isFavorite")
    val isFavorite: Boolean? = false,
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "price")
    val price: Int? = 0,
    @Json(name = "status")
    val status: Status? = Status()
)