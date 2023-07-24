package com.example.tezmarket.data.remote.model.addadvertisement


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddAdvertisement(
    @Json(name = "message")
    val message: String? = ""
)