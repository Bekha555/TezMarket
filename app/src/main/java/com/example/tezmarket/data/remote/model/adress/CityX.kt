package com.example.tezmarket.data.remote.model.adress


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityX(
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "title")
    val title: String? = ""
)