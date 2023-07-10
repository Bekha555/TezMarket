package com.example.tezmarket.data.remote.model.products

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductsData(
    val `data`: List<Data>? = emptyList(),
    val links: Links,
    val meta: Meta
)