package com.example.tezmarket.utils

import com.example.tezmarket.data.remote.TezMarketApi
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(
        chain
            .request()
            .newBuilder()
            .apply {
                addHeader("Accept", "application/json")
//                addHeader("Authorization", "Bearer ${TezMarketApi.AuthorizationToken}")
                addHeader("Web", 1.toString())
            }.build()
    )
}