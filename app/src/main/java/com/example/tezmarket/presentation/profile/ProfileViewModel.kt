package com.example.tezmarket.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tezmarket.data.remote.model.AttributesByCategory.AttributesById
import com.example.tezmarket.data.remote.model.user.UserData
import com.example.tezmarket.data.repositoryimpl.ProfileRepositoryImpl
import com.example.tezmarket.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.tcell.tcellexchange.utils.UiState
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileRepositoryImpl: ProfileRepositoryImpl) :
    ViewModel() {

    var userUiState by mutableStateOf(UiState<UserData>())
        private set
    var attributesById by mutableStateOf(UiState<AttributesById>())


    init {
        getUser()
    }


    fun getUser() {
        viewModelScope.launch {
            profileRepositoryImpl.getUser().collect { result ->
                userUiState = when (result) {
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

    fun getAttributesById(attributesId: Int) {
        viewModelScope.launch {
            profileRepositoryImpl.getAttributesById(attributesId).collect { result ->
                attributesById = when (result) {
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