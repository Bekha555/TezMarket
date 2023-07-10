package com.example.tezmarket.data.remote.model.shops

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meta(
    val current_page: Int? = 0,
    val from: Int? = 0,
    val last_page: Int? = 0,
    val links: List<Link>? = listOf(),
    val path: String? = "",
    val per_page: Int? = 0,
    val to: Int? = 0,
    val total: Int? = 0
)