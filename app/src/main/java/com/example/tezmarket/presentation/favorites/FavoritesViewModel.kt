package com.example.tezmarket.presentation.favorites

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tezmarket.data.remote.model.favorites.Data
import com.example.tezmarket.data.remote.model.favorites.FavoriteProducts
import com.example.tezmarket.data.remote.model.favorites.FavoritesToggle
import com.example.tezmarket.data.repositoryimpl.FavoriteRepositoryImpl
import com.example.tezmarket.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tj.tcell.tcellexchange.utils.UiState
import javax.inject.Inject


@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoriteRepositoryImpl: FavoriteRepositoryImpl,
) :
    ViewModel() {

    var favoritesToggleState by mutableStateOf(UiState<FavoritesToggle>())
        private set

    var favoriteProductsUiState by mutableStateOf(UiState<FavoriteProducts>())
        private set

    private val _favoriteProducts = MutableStateFlow<List<Data>>(emptyList())

    val favoriteProducts: StateFlow<List<Data>>
        get() = _favoriteProducts


    fun favoritesToggle(productId: Int, productType: String) {
        viewModelScope.launch {
            favoriteRepositoryImpl.favoritesToggle(productType = productType, productId = productId)
                .collect { result ->
                    favoritesToggleState = when (result) {
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
            getFavoriteProducts()
        }
    }


    fun getFavoriteProducts() {
        viewModelScope.launch {
            favoriteRepositoryImpl.getFavoritesProducts().collect { result ->
                favoriteProductsUiState = when (result) {
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
            _favoriteProducts.value = favoriteProductsUiState.data?.data ?: emptyList()
        }
    }


}