package com.example.tezmarket.data.remote.model.productrating


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Client(
    @Json(name = "avatar")
    val avatar: Any? = Any(),
    @Json(name = "name")
    val name: Any? = Any()
)