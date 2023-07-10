package com.example.tezmarket.data.remote.model.shops

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Links(
    val first: String? = "",
    val last: String? = "",
    val next: Any? = Any(),
    val prev: Any? = Any()
)