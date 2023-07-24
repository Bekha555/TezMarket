package com.example.tezmarket.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tezmarket.data.remote.model.addadvertisement.AddAdvertisement
import com.example.tezmarket.data.remote.model.myadvertisements.MyAdvertisementsData
import com.example.tezmarket.data.remote.model.uploadFiles.UploadFiles
import com.example.tezmarket.data.repositoryimpl.AdvertisementRepositoryImpl
import com.example.tezmarket.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.tcell.tcellexchange.utils.UiState
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AdvertisementViewModel @Inject constructor(
    private val advertisementRepositoryImpl: AdvertisementRepositoryImpl
) : ViewModel() {
    var myAdvertisementsUiState by mutableStateOf(UiState<MyAdvertisementsData>())
        private set
    var addAdvertisementUiState by mutableStateOf(UiState<AddAdvertisement>())
        private set
    var uploadFilesUiState by mutableStateOf(UiState<UploadFiles>())
        private set

    init {
        getMyAdvertisements()
    }

    fun getMyAdvertisements() {
        viewModelScope.launch {
            advertisementRepositoryImpl.getMyAdvertisements().collect { result ->
                myAdvertisementsUiState = when (result) {
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

    fun addAdvertisement(
        title: String,
        price: Int,
        category_id: Int,
        description: String,
        images: String,
        attribute_id: Int
    ) {
        viewModelScope.launch {
            advertisementRepositoryImpl.addAdvertisement(
                title,
                price,
                category_id,
                description,
                images,
                attribute_id
            ).collect { result ->
                addAdvertisementUiState = when (result) {
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

    fun uploadFile(
        file: File
    ) {
        viewModelScope.launch {
            advertisementRepositoryImpl.uploadFile(
                file
            ).collect { result ->
                uploadFilesUiState = when (result) {
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