package com.example.tezmarket.data.remote.model.myadvertisements


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Status(
    @Json(name = "color")
    val color: String? = "",
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "name")
    val name: String? = ""
)