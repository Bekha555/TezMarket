package com.example.tezmarket.data.remote.model.AttributesByCategory


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "name")
    val name: Name? = Name()
)