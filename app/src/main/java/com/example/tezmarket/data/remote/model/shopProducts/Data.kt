package com.example.tezmarket.data.remote.model.shopProducts


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "addedToCart")
    val addedToCart: Boolean? = false,
    @Json(name = "avgRating")
    val avgRating: Int? = 0,
    @Json(name = "category")
    val category: Category? = Category(),
    @Json(name = "created_at")
    val createdAt: String? = "",
    @Json(name = "discountedPrice")
    val discountedPrice: Int? = 0,
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "image")
    val image: String? = "",
    @Json(name = "isFavorite")
    val isFavorite: Boolean? = false,
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "percentageDiscount")
    val percentageDiscount: Int? = 0,
    @Json(name = "price")
    val price: Int? = 0,
    @Json(name = "quantity")
    val quantity: Int? = 0,
    @Json(name = "withDiscount")
    val withDiscount: Boolean? = false
)