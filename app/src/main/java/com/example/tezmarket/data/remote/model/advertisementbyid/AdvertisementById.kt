package com.example.tezmarket.data.remote.model.advertisementbyid


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AdvertisementById(
    @Json(name = "data")
    val `data`: Data? = Data()
)