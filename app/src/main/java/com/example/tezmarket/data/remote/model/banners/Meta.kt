package com.example.tezmarket.data.remote.model.banners

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meta(
    val current_page: Int? = 1,
    val from: Int? = 1,
    val last_page: Int? = 1,
    val links: List<Link>? = emptyList(),
    val path: String? = "",
    val per_page: Int? = 1,
    val to: Int? = 0,
    val total: Int? = 0
)