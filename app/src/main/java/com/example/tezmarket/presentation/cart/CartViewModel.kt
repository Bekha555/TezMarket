package com.example.tezmarket.presentation.cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tezmarket.data.remote.model.addcart.AddCartProduct
import com.example.tezmarket.data.remote.model.cart.Cart
import com.example.tezmarket.data.remote.model.cart.Data
import com.example.tezmarket.data.remote.model.modcart.ModCartProduct
import com.example.tezmarket.data.repositoryimpl.CartRepositoryImpl
import com.example.tezmarket.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tj.tcell.tcellexchange.utils.UiState
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepositoryImpl: CartRepositoryImpl
) :
    ViewModel() {

    var cartUiState by mutableStateOf(UiState<Cart>())
        private set

    var addCartUiState by mutableStateOf(UiState<AddCartProduct>())
        private set

    var modCartUiState by mutableStateOf(UiState<ModCartProduct>())
        private set


    private val _cartProducts = MutableStateFlow<List<Data>>(emptyList())

    val cartProducts: MutableStateFlow<List<Data>>
        get() = _cartProducts


    fun getAllCart() {
        viewModelScope.launch {
            cartRepositoryImpl.getAllCart().collect { result ->
                cartUiState = when (result) {
                    is Resource.Success -> {
                        UiState(data = result.content)
                    }

                    is Resource.Loading -> {
                        UiState(isLoading = true)
                    }

                    is Resource.Error -> {
                        UiState(error = result.message)
                    }

                    is Resource.NetworkError -> {
                        UiState(unknownError = result.exception.toString())
                    }
                }
            }
            _cartProducts.value = cartUiState.data?.data ?: emptyList()
        }
    }

    fun addCartProduct(productId: Int, productQuantity: Int) {
        viewModelScope.launch {
            cartRepositoryImpl.addCartProduct(
                productId = productId,
                productQuantity = productQuantity
            ).collect { result ->
                addCartUiState = when (result) {
                    is Resource.Success -> {
                        UiState(data = result.content)
                    }

                    is Resource.Loading -> {
                        UiState(isLoading = true)
                    }

                    is Resource.Error -> {
                        UiState(error = result.message)
                    }

                    is Resource.NetworkError -> {
                        UiState(unknownError = result.exception.toString())
                    }
                }
            }
            getAllCart()
        }
    }

    fun delCartProduct(cartId: Int) {
        viewModelScope.launch {
            cartRepositoryImpl.delCartProduct(cartId = cartId)
            getAllCart()
        }
    }

    fun modCartProduct(productId: Int, productQuantity: Int, cartId: Int) {
        viewModelScope.launch {
            cartRepositoryImpl.modCartProduct(
                productId = productId,
                productQuantity = productQuantity,
                cartId = cartId
            ).collect { result ->
                modCartUiState = when (result) {
                    is Resource.Success -> {
                        UiState(data = result.content)
                    }

                    is Resource.Loading -> {
                        UiState(isLoading = true)
                    }

                    is Resource.Error -> {
                        UiState(error = result.message)
                    }

                    is Resource.NetworkError -> {
                        UiState(unknownError = result.exception.toString())
                    }
                }
            }
            getAllCart()
        }
    }
}