package com.example.tezmarket.data.remote.model.modcart


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "attributes")
    val attributes: List<Any>? = listOf(),
    @Json(name = "avgRating")
    val avgRating: Int? = 0,
    @Json(name = "brand")
    val brand: Brand? = Brand(),
    @Json(name = "category")
    val category: Category? = Category(),
    @Json(name = "created_at")
    val createdAt: String? = "",
    @Json(name = "desc")
    val desc: String? = "",
    @Json(name = "discountedPrice")
    val discountedPrice: Any? = Any(),
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "images")
    val images: List<String>? = listOf(),
    @Json(name = "isFavorite")
    val isFavorite: Boolean? = false,
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "percentageDiscount")
    val percentageDiscount: Any? = Any(),
    @Json(name = "price")
    val price: Int? = 0,
    @Json(name = "quantity")
    val quantity: Int? = 0,
    @Json(name = "shop")
    val shop: Shop? = Shop(),
    @Json(name = "withDiscount")
    val withDiscount: Boolean? = false
)