package com.example.tezmarket.data.remote.model.user


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserData(
    @Json(name = "data")
    val `data`: Data = Data()
)