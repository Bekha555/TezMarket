package com.example.tezmarket.data.remote.model.products

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meta(
    val current_page: Int,
    val from: Int? = 0,
    val last_page: Int? = 0,
    val links: List<Link>? = emptyList(),
    val path: String? = "",
    val per_page: Int,
    val to: Int? = 0,
    val total: Int? = 0
)