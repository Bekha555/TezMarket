package com.example.tezmarket.data.repositoryimpl

import com.example.tezmarket.data.remote.TezMarketApi
import com.example.tezmarket.data.remote.model.addcart.AddCartProduct
import com.example.tezmarket.data.remote.model.cart.Cart
import com.example.tezmarket.data.remote.model.delcart.DelCartProduct
import com.example.tezmarket.data.remote.model.modcart.ModCartProduct
import com.example.tezmarket.domain.CartRepository
import com.example.tezmarket.utils.Resource
import com.example.tezmarket.utils.SafeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(private val tezMarketApi: TezMarketApi) :
    CartRepository,
    SafeApiCall() {
    override suspend fun getAllCart(): Flow<Resource<Cart>> = call {
        tezMarketApi.getCartProducts()
    }

    override suspend fun addCartProduct(
        productId: Int,
        productQuantity: Int
    ): Flow<Resource<AddCartProduct>> = call {
        tezMarketApi.addCartProduct(productId = productId, productQuantity = productQuantity)
    }

    override suspend fun modCartProduct(
        productId: Int,
        productQuantity: Int,
        cartId: Int
    ): Flow<Resource<ModCartProduct>> = call {
        tezMarketApi.modCartProduct(productId = productId, productQuantity = productQuantity, cartId = cartId)
    }

    override suspend fun delCartProduct(cartId: Int) {
        tezMarketApi.delCartProduct(cartId = cartId)
    }


}