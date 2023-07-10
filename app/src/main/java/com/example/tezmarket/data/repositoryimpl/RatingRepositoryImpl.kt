package com.example.tezmarket.data.repositoryimpl

import com.example.tezmarket.data.remote.TezMarketApi
import com.example.tezmarket.data.remote.model.productrating.AddProductRating
import com.example.tezmarket.data.remote.model.productrating.ProductRatingData
import com.example.tezmarket.domain.RatingRepository
import com.example.tezmarket.utils.Resource
import com.example.tezmarket.utils.SafeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RatingRepositoryImpl @Inject constructor(private val tezMarketApi: TezMarketApi) :
    RatingRepository, SafeApiCall() {
    override suspend fun addProductRating(
        productId: Int,
        productComment: String,
        productRating: Int
    ): Flow<Resource<AddProductRating>> = call {
        tezMarketApi.addProductRating(
            productId = productId,
            productComment = productComment, productRating = productRating
        )
    }

    override suspend fun getProductRating(productId: Int): Flow<Resource<ProductRatingData>> = call {
            tezMarketApi.getProductRating(productId = productId)
        }
}