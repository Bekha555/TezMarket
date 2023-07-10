package com.example.tezmarket.data.remote.model.categories


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "image")
    val image: String? = "",
    @Json(name = "name")
    val name: String? = ""
)