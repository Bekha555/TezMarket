package com.example.tezmarket.data.remote.model.products

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    val avgRating: Int? = 0,
    val category: Category = Category(),
    val created_at: String? = "",
    val discountedPrice: Int? = 0,
    val id: Int? = 0,
    val image: String? = "",
    val isFavorite: Boolean? = false,
    val name: String? = "",
    val percentageDiscount: Int? = 0,
    val price: Int? = 0,
    val quantity: Int? = 0,
    val withDiscount: Boolean? = false
)