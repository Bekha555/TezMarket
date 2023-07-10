package com.example.tezmarket.data.remote.model.adress


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataX(
    @Json(name = "id")
    val id: Int? = 1,
    @Json(name = "city")
    val city: CityX? = CityX(),
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "region")
    val region: String? = "",
    @Json(name = "zip_code")
    val zipCode: String? = ""
)