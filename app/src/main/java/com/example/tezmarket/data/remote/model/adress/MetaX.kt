package com.example.tezmarket.data.remote.model.adress


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MetaX(
    @Json(name = "current_page")
    val currentPage: Int? = 0,
    @Json(name = "from")
    val from: Int? = 0,
    @Json(name = "last_page")
    val lastPage: Int? = 0,
    @Json(name = "links")
    val links: List<LinkX>? = listOf(),
    @Json(name = "path")
    val path: String? = "",
    @Json(name = "per_page")
    val perPage: Int? = 0,
    @Json(name = "to")
    val to: Int? = 0,
    @Json(name = "total")
    val total: Int? = 0
)