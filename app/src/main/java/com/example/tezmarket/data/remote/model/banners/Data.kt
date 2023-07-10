package com.example.tezmarket.data.remote.model.banners

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    val id: Int? = 0,
    val image: String
)