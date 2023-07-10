package com.example.tezmarket.data.remote.model.banners

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Links(
    val first: String? = "",
    val last: String? = "",
    val next: String? = "",
    val prev: Any? = ""
)