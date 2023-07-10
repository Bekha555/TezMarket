package com.example.tezmarket.data.remote.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tezmarket.data.remote.TezMarketApi
import com.example.tezmarket.data.remote.model.prodouctsbycategory.Data
import retrofit2.HttpException
import java.io.IOException

class ProductsByCategoryPagingSource(
    private val tezMarketApi: TezMarketApi,
    private val categoryId: Int
) : PagingSource<Int, Data>() {

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val nextPage = params.key ?: 1
            val response =
                tezMarketApi.getProductsByCategory(categoryId = categoryId, page = nextPage).body()
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