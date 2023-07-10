package com.example.tezmarket.presentation.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tezmarket.data.remote.model.categories.Categories
import com.example.tezmarket.data.remote.model.user.UserData
import com.example.tezmarket.data.repositoryimpl.CategoriesRepositoryImpl
import com.example.tezmarket.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.tcell.tcellexchange.utils.UiState
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesRepositoryImpl: CategoriesRepositoryImpl
) :
    ViewModel() {

    var categoriesUiState by mutableStateOf(UiState<Categories>())
        private set
    var userUiState by mutableStateOf(UiState<UserData>())
        private set

    init {
        getAllCategories()
    }


    fun getAllCategories() {
        viewModelScope.launch {
            categoriesRepositoryImpl.getAllCategories().collect { result ->
                categoriesUiState = when (result) {
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