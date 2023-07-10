package com.example.tezmarket.domain

import androidx.paging.PagingSource
import com.example.tezmarket.data.remote.model.shops.Data
import com.example.tezmarket.data.remote.model.shops.ShopsData
import kotlinx.coroutines.flow.Flow
import com.example.tezmarket.utils.Resource


interface ShopsRepository {

    fun getAllShops() : PagingSource<Int, Data>
}