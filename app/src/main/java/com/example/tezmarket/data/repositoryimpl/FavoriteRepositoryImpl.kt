package com.example.tezmarket.data.repositoryimpl

import com.example.tezmarket.data.remote.TezMarketApi
import com.example.tezmarket.data.remote.model.favorites.FavoriteProducts
import com.example.tezmarket.data.remote.model.favorites.FavoritesToggle
import com.example.tezmarket.domain.FavoriteRepository
import com.example.tezmarket.utils.Resource
import com.example.tezmarket.utils.SafeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(private val tezMarketApi: TezMarketApi) :
    FavoriteRepository, SafeApiCall() {

    override suspend fun getFavoritesProducts(): Flow<Resource<FavoriteProducts>> = call {
        tezMarketApi.getFavoriteProducts()
    }

    override suspend fun favoritesToggle(
        productId: Int,
        productType: String
    ): Flow<Resource<FavoritesToggle>> = call {
        tezMarketApi.favoritesToggle(productId = productId, productType = productType)
    }


}