package com.example.tezmarket.data.remote.model.shops

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShopsData(
    val `data`: List<Data>? = listOf(),
    val links: Links? = Links(),
    val meta: Meta? = Meta()
)