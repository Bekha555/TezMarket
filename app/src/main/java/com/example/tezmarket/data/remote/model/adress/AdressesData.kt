package com.example.tezmarket.data.remote.model.adress


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AdressesData(
    @Json(name = "data")
    val `data`: List<DataX>? = listOf(),
    @Json(name = "links")
    val links: LinksX? = LinksX(),
    @Json(name = "meta")
    val meta: MetaX? = MetaX()
)