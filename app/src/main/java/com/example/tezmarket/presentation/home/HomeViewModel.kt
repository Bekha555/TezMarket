package com.example.tezmarket.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tezmarket.data.remote.model.advertisementbyid.AdvertisementById
import com.example.tezmarket.data.remote.model.banners.BannersData
import com.example.tezmarket.data.remote.model.filterdata.FilterData
import com.example.tezmarket.data.remote.model.filteredata.FilteredProducts
import com.example.tezmarket.data.remote.model.otherAdvertisements.OtherAdvertisementData
import com.example.tezmarket.data.remote.model.prodouctsbycategory.Data
import com.example.tezmarket.data.remote.model.productbyid.ProductById
import com.example.tezmarket.data.remote.model.shopProducts.ShopProductsData
import com.example.tezmarket.data.remote.model.shopsbyid.ShopByIdData
import com.example.tezmarket.data.remote.model.simularproducts.SimularProduct
import com.example.tezmarket.data.repositoryimpl.ProductRepositoryImpl
import com.example.tezmarket.data.repositoryimpl.ShopsRepositoryImpl
import com.example.tezmarket.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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

    var advertisementByIduiState by mutableStateOf(UiState<AdvertisementById>())

    var otherAdvertisementUiState by mutableStateOf(UiState<OtherAdvertisementData>())

    var shopByIdUiState by mutableStateOf(UiState<ShopByIdData>())

    var shopProductUiState by mutableStateOf(UiState<ShopProductsData>())

    var simularProductUiState by mutableStateOf(UiState<SimularProduct>())
        private set

    var filteredDataUiState by mutableStateOf(UiState<FilteredProducts>())
        private set

    var filterUiState by mutableStateOf(UiState<FilterData>())
        private set

    fun getHomeData() {
        getAllBanners()
    }


    val _filteredData = mutableStateMapOf<String, Any>()

    fun updateData(key: String, value: Any) {
        _filteredData[key] = value
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

    val advertisementItems = Pager(
        config = PagingConfig(
        pageSize = ITEMS_PER_PAGE,
        prefetchDistance = PREFETCH_DISTANCE,
        enablePlaceholders = false
    ),
        pagingSourceFactory = {
            productRepositoryImpl.getAllAdvertisements()
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

    fun getAdvertisementById(advertisement_id: Int) {
        viewModelScope.launch {
            productRepositoryImpl.getAdvertisementById(advertisement_id).collect { result ->
                advertisementByIduiState = when (result) {
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

    fun getOtherAdvertisement(advertisement_id: Int) {
        viewModelScope.launch {
            productRepositoryImpl.getOtherAdvertisement(advertisement_id).collect { result ->
                otherAdvertisementUiState = when (result) {
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

    fun getShopById(shop_id: Int) {
        viewModelScope.launch {
            productRepositoryImpl.getShopById(shop_id).collect { result ->
                shopByIdUiState = when (result) {
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

    fun getShopProduct(shop_id: Int) {
        viewModelScope.launch {
            productRepositoryImpl.getShopProduct(shop_id).collect { result ->
                shopProductUiState = when (result) {
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

    fun getSimularProducts(productId: Int) {2
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


    fun getFilteredProducts() {
        viewModelScope.launch {
            productRepositoryImpl.getFilteredProducts(filterData = _filteredData)
                .collect { result ->
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


    fun getFilterData() {
        viewModelScope.launch {
            productRepositoryImpl.getFilterData().collect { result ->
                filterUiState = when (result) {
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