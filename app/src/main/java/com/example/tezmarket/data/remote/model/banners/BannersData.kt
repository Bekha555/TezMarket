package com.example.tezmarket.data.remote.model.banners

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BannersData(
    val `data`: List<Data>? = emptyList(),
    val links: Links = Links(),
    val meta: Meta = Meta()
)