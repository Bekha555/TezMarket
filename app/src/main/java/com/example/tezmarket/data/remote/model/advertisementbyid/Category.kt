package com.example.tezmarket.data.remote.model.advertisementbyid


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Category(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "image")
    val image: String = "",
    @Json(name = "parent_id")
    val parentId: Any = Any(),
    @Json(name = "title")
    val title: String = ""
)