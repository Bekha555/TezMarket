package com.example.tezmarket.data.repositoryimpl

import com.example.tezmarket.data.remote.TezMarketApi
import com.example.tezmarket.data.remote.model.categories.Categories
import com.example.tezmarket.data.remote.model.filterdata.FilterData
import com.example.tezmarket.domain.CategoriesRepository
import com.example.tezmarket.utils.Resource
import com.example.tezmarket.utils.SafeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(private val tezMarketApi: TezMarketApi) :
    CategoriesRepository,
    SafeApiCall() {
    override suspend fun getAllCategories(): Flow<Resource<Categories>> = call {
        tezMarketApi.getAllCategories()
    }

    override suspend fun getFilterData(): Flow<Resource<FilterData>> = call {
        tezMarketApi.getFilterData()
    }
}