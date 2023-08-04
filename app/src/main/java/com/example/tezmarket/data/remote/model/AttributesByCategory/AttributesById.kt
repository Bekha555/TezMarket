package com.example.tezmarket.data.remote.model.AttributesByCategory


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AttributesById(
    @Json(name = "data")
    val `data`: List<Data>? = listOf()
)