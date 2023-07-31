package com.example.tezmarket.data.remote.model.deleteAdvertisement


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeleteAdvertisementData(
    @Json(name = "message")
    val message: String? = ""
)