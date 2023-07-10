package com.example.tezmarket.data.remote.model.modcart


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Shop(
    @Json(name = "address")
    val address: String? = "",
    @Json(name = "city")
    val city: City? = City(),
    @Json(name = "description")
    val description: String? = "",
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "logo")
    val logo: String? = "",
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "phone")
    val phone: String? = ""
)