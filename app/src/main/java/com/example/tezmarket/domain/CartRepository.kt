package com.example.tezmarket.domain

import com.example.tezmarket.data.remote.model.addcart.AddCartProduct
import com.example.tezmarket.data.remote.model.cart.Cart
import com.example.tezmarket.data.remote.model.delcart.DelCartProduct
import com.example.tezmarket.data.remote.model.modcart.ModCartProduct
import com.example.tezmarket.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun getAllCart(): Flow<Resource<Cart>>
    suspend fun addCartProduct(
        productId: Int,
        productQuantity: Int
    ): Flow<Resource<AddCartProduct>>

    suspend fun modCartProduct(
        productId: Int,
        productQuantity: Int,
        cartId: Int
    ): Flow<Resource<ModCartProduct>>

    suspend fun delCartProduct(cartId: Int)
}