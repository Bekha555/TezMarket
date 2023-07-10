package com.example.tezmarket.data.remote.model.categories


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Categories(
    @Json(name = "data")
    val `data`: List<Data> = listOf()
)