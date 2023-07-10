package com.example.tezmarket.data.repositoryimpl

import androidx.paging.PagingSource
import com.example.tezmarket.data.remote.model.shops.ShopsData
import com.example.tezmarket.domain.ShopsRepository
import kotlinx.coroutines.flow.Flow
import com.example.tezmarket.data.remote.TezMarketApi
import com.example.tezmarket.data.remote.model.ShopsPagingSource
import com.example.tezmarket.data.remote.model.shops.Data
import com.example.tezmarket.utils.Resource
import com.example.tezmarket.utils.SafeApiCall
import javax.inject.Inject

class ShopsRepositoryImpl @Inject constructor(private val tezMarketApi: TezMarketApi) :
    ShopsRepository, SafeApiCall() {
    override fun getAllShops(): PagingSource<Int, Data> = ShopsPagingSource(tezMarketApi)
}