package com.example.tezmarket.data.remote.model.shops

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Data(
    val address: String? = "",
    val id: Int? = 0,
    val logo: String? = "",
    val name: String? = ""
)