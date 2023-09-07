package com.example.tezmarket.domain

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.tezmarket.data.remote.model.advertisementbyid.AdvertisementById
import com.example.tezmarket.data.remote.model.banners.BannersData
import com.example.tezmarket.data.remote.model.filterdata.FilterData
import com.example.tezmarket.data.remote.model.filteredata.FilteredProducts
import com.example.tezmarket.data.remote.model.otherAdvertisements.OtherAdvertisementData
import com.example.tezmarket.data.remote.model.productbyid.ProductById
import com.example.tezmarket.data.remote.model.recproducts.Data
import com.example.tezmarket.data.remote.model.shopProducts.ShopProductsData
import com.example.tezmarket.data.remote.model.shopsbyid.ShopByIdData
import com.example.tezmarket.data.remote.model.simularproducts.SimularProduct
import com.example.tezmarket.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getAllProducts(): PagingSource<Int, com.example.tezmarket.data.remote.model.products.Data>

    suspend fun getAllBanners(): Flow<Resource<BannersData>>

    suspend fun getProductById(productId: Int): Flow<Resource<ProductById>>

    suspend fun getAdvertisementById(advertisement_id: Int): Flow<Resource<AdvertisementById>>

    suspend fun getOtherAdvertisement(advertisement_id: Int): Flow<Resource<OtherAdvertisementData>>

    suspend fun getShopById(shop_id: Int): Flow<Resource<ShopByIdData>>

    suspend fun getShopProduct(shop_id: Int): Flow<Resource<ShopProductsData>>
  
    fun getDiscProducts(): PagingSource<Int, com.example.tezmarket.data.remote.model.discProducts.Data>

    suspend fun getSimularProducts(productId: Int): Flow<Resource<SimularProduct>>

    fun getAllAdvertisements(): PagingSource<Int, com.example.tezmarket.data.remote.model.advertisements.Data>

    fun getRecProducts(): PagingSource<Int, Data>

    fun getProductByCategory(categoryId: Int) : Flow<PagingData<com.example.tezmarket.data.remote.model.prodouctsbycategory.Data>>

    suspend fun getFilteredProducts(filterData: Map<String, Any>): Flow<Resource<FilteredProducts>>
    suspend fun getFilterData(): Flow<Resource<FilterData>>

}