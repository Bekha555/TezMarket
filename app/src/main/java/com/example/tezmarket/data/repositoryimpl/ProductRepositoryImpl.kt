package com.example.tezmarket.data.repositoryimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.tezmarket.data.remote.TezMarketApi
import com.example.tezmarket.data.remote.model.AdvertisementsPagingSource
import com.example.tezmarket.data.remote.model.DiscProductsPagingSource
import com.example.tezmarket.data.remote.model.ProductsByCategoryPagingSource
import com.example.tezmarket.data.remote.model.ProductsPagingSource
import com.example.tezmarket.data.remote.model.RecProductsPagingSource
import com.example.tezmarket.data.remote.model.advertisementbyid.AdvertisementById
import com.example.tezmarket.data.remote.model.banners.BannersData
import com.example.tezmarket.data.remote.model.filterdata.FilterData
import com.example.tezmarket.data.remote.model.filteredata.FilteredProducts
import com.example.tezmarket.data.remote.model.productbyid.ProductById
import com.example.tezmarket.data.remote.model.recproducts.Data
import com.example.tezmarket.data.remote.model.shopsbyid.ShopByIdData
import com.example.tezmarket.data.remote.model.simularproducts.SimularProduct
import com.example.tezmarket.domain.ProductsRepository
import com.example.tezmarket.utils.Resource
import com.example.tezmarket.utils.SafeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val tezMarketApi: TezMarketApi) :
    ProductsRepository, SafeApiCall() {
    override fun getAllProducts(): PagingSource<Int, com.example.tezmarket.data.remote.model.products.Data> =
        ProductsPagingSource(tezMarketApi)

    override suspend fun getAllBanners(): Flow<Resource<BannersData>> = call {
        tezMarketApi.getAllBanners()
    }

    override suspend fun getProductById(productId: Int): Flow<Resource<ProductById>> = call {
        tezMarketApi.getProductById(productId = productId)
    }

    override suspend fun getAdvertisementById(advertisement_id: Int): Flow<Resource<AdvertisementById>> = call {
        tezMarketApi.getAdvertisementById(advertisement_id = advertisement_id)
    }

    override suspend fun getShopById(shop_id: Int): Flow<Resource<ShopByIdData>> = call {
        tezMarketApi.getShopById(shop_id = shop_id)
    }

    override fun getDiscProducts(): PagingSource<Int, com.example.tezmarket.data.remote.model.discProducts.Data> =
        DiscProductsPagingSource(tezMarketApi)

    override suspend fun getSimularProducts(productId: Int): Flow<Resource<SimularProduct>> = call {
        tezMarketApi.getSimularProducts(productId = productId)
    }

    override fun getAllAdvertisements(): PagingSource<Int, com.example.tezmarket.data.remote.model.advertisements.Data> =
        AdvertisementsPagingSource(tezMarketApi)

    override fun getRecProducts(): PagingSource<Int, Data> =
        RecProductsPagingSource(tezMarketApi)

    override fun getProductByCategory(categoryId: Int): Flow<PagingData<com.example.tezmarket.data.remote.model.prodouctsbycategory.Data>> {
        return Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE,
                prefetchDistance = PREFETCH_DISTANCE
            ),
            pagingSourceFactory = {
                ProductsByCategoryPagingSource(tezMarketApi, categoryId = categoryId)
            }
        ).flow
    }

    override suspend fun getFilteredProducts(filterData: Map<String, Any>): Flow<Resource<FilteredProducts>> = call{
        tezMarketApi.getFilteredProducts(filterData)
    }

    override suspend fun getFilterData(): Flow<Resource<FilterData>> = call{
        tezMarketApi.getFilterData()
    }

    companion object {
        const val ITEMS_PER_PAGE = 6
        const val PREFETCH_DISTANCE = 1
    }

}