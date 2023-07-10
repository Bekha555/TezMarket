package com.example.tezmarket.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.tezmarket.data.remote.model.adress.AddAdress
import com.example.tezmarket.data.remote.model.adress.AdressById
import com.example.tezmarket.data.remote.model.adress.AdressesData
import com.example.tezmarket.data.remote.model.adress.UpdateAdress
import com.example.tezmarket.data.repositoryimpl.AdressRepositoryImpl
import com.example.tezmarket.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tj.tcell.tcellexchange.utils.UiState
import javax.inject.Inject

@HiltViewModel
class AdressViewModel @Inject constructor(private val adressRepositoryImpl: AdressRepositoryImpl) :
    ViewModel() {

    var addAdressUiState by mutableStateOf(UiState<AddAdress>())
        private set

    var adressesState by mutableStateOf(UiState<AdressesData>())
        private set

    var updateAdressUiState by mutableStateOf(UiState<UpdateAdress>())
        private set

    var adressByIdUiState by mutableStateOf(UiState<AdressById>())
        private set


    private val ITEMS_PER_PAGE = 6
    private val PREFETCH_DISTANCE = 1

    val adressItems = Pager(
        config = PagingConfig(
            pageSize = ITEMS_PER_PAGE,
            prefetchDistance = PREFETCH_DISTANCE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            adressRepositoryImpl.getAllAdresses()
        },
    ).flow.cachedIn(viewModelScope)


    fun getAdresses() {
        viewModelScope.launch {
            adressRepositoryImpl.getAdresses().collect { result ->
                adressesState = when (result) {
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

    fun addAdress(
        adressName: String,
        cityId: Int,
        regionName: String,
        zipCode: Int
    ) {
        viewModelScope.launch {
            adressRepositoryImpl.addAdress(adressName, cityId, regionName, zipCode)
                .collect { result ->
                    addAdressUiState = when (result) {
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


    fun updateAdress(
        adressId: Int,
        adressName: String,
        cityId: Int,
        regionName: String,
        zipCode: Int
    ) {
        viewModelScope.launch {
            adressRepositoryImpl.updateAdress(adressId, adressName, cityId, regionName, zipCode)
                .collect { result ->
                    updateAdressUiState = when (result) {
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

    fun getAdressById(adressId: Int) {
        viewModelScope.launch {
            adressRepositoryImpl.getAddressById(adressId).collect { result ->
                adressByIdUiState = when (result) {
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
