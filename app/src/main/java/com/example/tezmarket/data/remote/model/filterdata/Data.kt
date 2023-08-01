package com.example.tezmarket.data.remote.model.filterdata


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "label")
    val label: String? = "",
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "value")
    val value: String? = ""
)