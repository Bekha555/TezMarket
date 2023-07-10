package com.example.tezmarket.data.remote.model.shops

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Link(
    val active: Boolean? = false,
    val label: String? = "",
    val url: String? = ""
)