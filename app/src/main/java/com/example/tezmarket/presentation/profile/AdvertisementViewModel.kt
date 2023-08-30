package com.example.tezmarket.presentation.profile

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tezmarket.data.remote.model.addadvertisement.AddAdvertisement
import com.example.tezmarket.data.remote.model.addadvertisement.Attribute
import com.example.tezmarket.data.remote.model.deleteAdvertisement.DeleteAdvertisementData
import com.example.tezmarket.data.remote.model.myadvertisements.Data
import com.example.tezmarket.data.remote.model.myadvertisements.MyAdvertisementsData
import com.example.tezmarket.data.remote.model.uploadFiles.UploadFiles
import com.example.tezmarket.data.repositoryimpl.AdvertisementRepositoryImpl
import com.example.tezmarket.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import tj.tcell.tcellexchange.utils.UiState
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AdvertisementViewModel @Inject constructor(
    private val advertisementRepositoryImpl: AdvertisementRepositoryImpl
) : ViewModel() {


    var tempImageLinks = mutableListOf<String>()
    var myAdvertisementsUiState by mutableStateOf(UiState<MyAdvertisementsData>())
        private set
    var addAdvertisementUiState by mutableStateOf(UiState<AddAdvertisement>())
        private set
    var uploadFilesUiState by mutableStateOf(UiState<UploadFiles>())
        private set
    var deleteAdvertisementUiState by mutableStateOf(UiState<DeleteAdvertisementData>())

    private val _advertisements = MutableStateFlow<List<Data>>(emptyList())

    val advertisements: MutableStateFlow<List<Data>>
        get() = _advertisements


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
            _advertisements.value = myAdvertisementsUiState.data?.data ?: emptyList()
        }
    }

    fun addAdvertisement(
        map: Map<String, Any>
    ) {
        viewModelScope.launch {
            advertisementRepositoryImpl.addAdvertisement(
                map
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

    fun deleteAdvertisement(
        advertisement_id: Int
    ) {
        viewModelScope.launch {
            advertisementRepositoryImpl.deleteAddvertisement(
                advertisement_id
            ).collect { result ->
                deleteAdvertisementUiState = when (result) {
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
            getMyAdvertisements()
        }
    }


    fun uploadFile(
        file: MultipartBody.Part
    ) {
        viewModelScope.launch {
            advertisementRepositoryImpl.uploadFile(
                file
            ).collect { result ->
                Log.d("File upload result", result.toString())
                uploadFilesUiState = when (result) {
                    is Resource.Success -> {
                        tempImageLinks.add(result.content.filePath ?: "")
                        Log.d("Size of imageLinks in viewmodel", tempImageLinks.size.toString())
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