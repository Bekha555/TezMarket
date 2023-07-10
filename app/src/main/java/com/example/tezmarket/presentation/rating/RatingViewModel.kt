package com.example.tezmarket.presentation.rating


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tezmarket.data.remote.model.productrating.AddProductRating
import com.example.tezmarket.data.remote.model.productrating.ProductRatingData
import com.example.tezmarket.data.repositoryimpl.RatingRepositoryImpl
import com.example.tezmarket.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.tcell.tcellexchange.utils.UiState
import javax.inject.Inject

@HiltViewModel
class RatingViewModel @Inject constructor(private val ratingRepositoryImpl: RatingRepositoryImpl) :
    ViewModel() {

    var addProductRatingUiState by mutableStateOf(UiState<AddProductRating>())
        private set

    var productRatingInfoUiState by mutableStateOf(UiState<ProductRatingData>())
        private set

    fun addProductRating(productId: Int, productComment: String, productRating: Int) {
        viewModelScope.launch {
            ratingRepositoryImpl.addProductRating(
                productRating = productRating,
                productId = productId,
                productComment = productComment
            ).collect { result ->
                addProductRatingUiState = when (result) {
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
        }
    }

    fun getProductRating(productId: Int) {
        viewModelScope.launch {
            ratingRepositoryImpl.getProductRating(productId = productId).collect { result ->
                productRatingInfoUiState = when (result) {
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
        }
    }

}