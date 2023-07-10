package com.example.tezmarket.data.remote.model.products

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Link(
    val active: Boolean,
    val label: String,
    val url: String? = ""
)