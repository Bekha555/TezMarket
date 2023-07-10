package com.example.tezmarket.domain

import com.example.tezmarket.data.remote.model.productrating.AddProductRating
import com.example.tezmarket.data.remote.model.productrating.ProductRatingData
import com.example.tezmarket.utils.Resource
import kotlinx.coroutines.flow.Flow


interface RatingRepository {
    suspend fun addProductRating(
        productId: Int,
        productComment: String,
        productRating: Int
    ): Flow<Resource<AddProductRating>>

    suspend fun getProductRating(productId: Int): Flow<Resource<ProductRatingData>>
}