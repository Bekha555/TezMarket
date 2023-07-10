package com.example.tezmarket.data.remote.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tezmarket.data.remote.TezMarketApi
import com.example.tezmarket.data.remote.model.products.Data
import retrofit2.HttpException
import java.io.IOException

class ProductsPagingSource(private val tezMarketApi: TezMarketApi) : PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val nextPage = params.key ?: 1
            val response = tezMarketApi.getAllProducts(page = nextPage).body()
            LoadResult.Page(
                data = response?.data ?: emptyList(),
                nextKey = if (response?.data?.isEmpty() == true) null else (response?.meta?.current_page
                    ?: 0) + 1,
                prevKey = if (response?.meta?.current_page == 1) null else (response?.meta?.current_page
                    ?: 0) - 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}