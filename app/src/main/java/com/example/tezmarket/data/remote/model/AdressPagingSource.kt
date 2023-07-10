package com.example.tezmarket.data.remote.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tezmarket.data.remote.TezMarketApi
import com.example.tezmarket.data.remote.model.adress.Data
import com.example.tezmarket.data.remote.model.adress.DataX
import retrofit2.HttpException
import java.io.IOException

class AdressPagingSource(private val tezMarketApi: TezMarketApi) : PagingSource<Int, DataX>() {

    override fun getRefreshKey(state: PagingState<Int, DataX>): Int? {
        return state.anchorPosition
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataX> {
        return try {
            val nextPage = params.key ?: 1
            val response = tezMarketApi.getAllAdresses(nextPage).body()
            LoadResult.Page(
                data = response?.data ?: emptyList(),
                nextKey = if (response?.data?.isEmpty() == true) null else (response?.meta?.currentPage
                    ?: 0) + 1,
                prevKey = if (response?.meta?.currentPage == 1) null else (response?.meta?.currentPage
                    ?: 0) - 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

}