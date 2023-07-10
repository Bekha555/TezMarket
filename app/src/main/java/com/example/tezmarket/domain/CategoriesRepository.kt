package com.example.tezmarket.domain


import com.example.tezmarket.data.remote.model.categories.Categories
import com.example.tezmarket.data.remote.model.filterdata.FilterData
import com.example.tezmarket.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun getAllCategories(): Flow<Resource<Categories>>

    suspend fun getFilterData(): Flow<Resource<FilterData>>
}