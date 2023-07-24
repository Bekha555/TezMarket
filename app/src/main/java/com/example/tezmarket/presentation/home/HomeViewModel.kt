package com.example.tezmarket.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tezmarket.data.remote.model.advertisements.Advertisements
import com.example.tezmarket.data.remote.model.banners.BannersData
import com.example.tezmarket.data.remote.model.discProducts.DiscProducts

import com.example.tezmarket.data.remote.model.filteredata.FilteredProducts
import com.example.tezmarket.data.remote.model.prodouctsbycategory.Data
import com.example.tezmarket.data.remote.model.productbyid.ProductById
import com.example.tezmarket.data.remote.model.products.ProductsData
import com.example.tezmarket.data.remote.model.simularproducts.SimularProduct
import com.example.tezmarket.data.repositoryimpl.ProductRepositoryImpl
import com.example.tezmarket.data.repositoryimpl.ShopsRepositoryImpl
import com.example.tezmarket.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tj.tcell.tcellexchange.utils.UiState
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepositoryImpl: ProductRepositoryImpl,
    private val shopsRepositoryImpl: ShopsRepositoryImpl
) :
    ViewModel() {

    var bannersUiState by mutableStateOf(UiState<BannersData>())
        private set

    var productByIdUiState by mutableStateOf(UiState<ProductById>())
        private set

    var simularProductUiState by mutableStateOf(UiState<SimularProduct>())
        private set

    var advertisementsUiState by mutableStateOf(UiState<Advertisements>())
        private set

    var filteredProductsUiState by mutableStateOf(UiState<FilteredProducts>())
        private set

    var filteredDataUiState by mutableStateOf(UiState<FilteredProducts>())
        private set

    fun getHomeData() {
        getAllBanners()
        getAllAdvertisements()
    }

    private val ITEMS_PER_PAGE = 6
    private val PREFETCH_DISTANCE = 1

    val recProductItems = Pager(
        config = PagingConfig(
            pageSize = ITEMS_PER_PAGE,
            prefetchDistance = PREFETCH_DISTANCE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            productRepositoryImpl.getRecProducts()
        },
    ).flow.cachedIn(viewModelScope)


    val productItems = Pager(
        config = PagingConfig(
            pageSize = ITEMS_PER_PAGE,
            prefetchDistance = PREFETCH_DISTANCE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            productRepositoryImpl.getAllProducts()
        },
    ).flow.cachedIn(viewModelScope)


    val discProductItems = Pager(
        config = PagingConfig(
            pageSize = ITEMS_PER_PAGE,
            prefetchDistance = PREFETCH_DISTANCE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            productRepositoryImpl.getDiscProducts()
        },
    ).flow.cachedIn(viewModelScope)


    val shopsItems = Pager(
        config = PagingConfig(
            pageSize = ITEMS_PER_PAGE,
            prefetchDistance = PREFETCH_DISTANCE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            shopsRepositoryImpl.getAllShops()
        },
    ).flow.cachedIn(viewModelScope)

    private val _productsByCategory = MutableStateFlow<PagingData<Data>>(PagingData.empty())
    val productsByCategory = _productsByCategory

    fun getProductsByCategory(categoryId: Int) {
        viewModelScope.launch {
            productRepositoryImpl.getProductByCategory(categoryId = categoryId).collect {
                _productsByCategory.value = it
            }
        }
    }

    fun getAllBanners() {
        viewModelScope.launch {
            productRepositoryImpl.getAllBanners().collect { result ->
                bannersUiState = when (result) {
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


    fun getProductById(productId: Int) {
        viewModelScope.launch {
            productRepositoryImpl.getProductById(productId).collect { result ->
                productByIdUiState = when (result) {
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


    fun getSimularProducts(productId: Int) {
        viewModelScope.launch {
            productRepositoryImpl.getSimularProducts(productId = productId).collect { result ->
                simularProductUiState = when (result) {
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

    fun getAllAdvertisements() {
        viewModelScope.launch {
            productRepositoryImpl.getAllAdvertisements().collect { result ->
                advertisementsUiState = when (result) {
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

    fun getFilteredProducts(filteredData: Map<String, Any>){
        viewModelScope.launch {
            productRepositoryImpl.getFilteredProducts(filteredData).collect{result ->
                filteredDataUiState = when (result) {
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