package com.example.tezmarket.data.remote.model.adress


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateAdress(
    @Json(name = "message")
    val message: String = ""
)