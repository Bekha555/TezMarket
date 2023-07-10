package com.example.tezmarket.domain

import com.example.tezmarket.data.remote.model.favorites.FavoriteProducts
import com.example.tezmarket.data.remote.model.favorites.FavoritesToggle
import com.example.tezmarket.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun getFavoritesProducts(): Flow<Resource<FavoriteProducts>>

    suspend fun favoritesToggle(productId: Int, productType: String): Flow<Resource<FavoritesToggle>>

}