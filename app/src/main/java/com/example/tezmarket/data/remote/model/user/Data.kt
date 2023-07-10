package com.example.tezmarket.data.remote.model.user


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "avatar")
    val avatar: Any? = Any(),
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "is_agree")
    val isAgree: Any? = Any(),
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "phone")
    val phone: String? = "",
    @Json(name = "subscriber_id")
    val subscriberId: Int? = 0

)