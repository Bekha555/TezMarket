package com.example.tezmarket.data.remote.model.cart


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Brand(
    @Json(name = "desc")
    val desc: Any? = Any(),
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "image")
    val image: String? = "",
    @Json(name = "title")
    val title: String? = ""
)