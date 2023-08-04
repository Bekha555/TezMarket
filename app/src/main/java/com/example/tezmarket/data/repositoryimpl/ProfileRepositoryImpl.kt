package com.example.tezmarket.data.repositoryimpl

import com.example.tezmarket.data.remote.TezMarketApi
import com.example.tezmarket.data.remote.model.AttributesByCategory.AttributesById
import com.example.tezmarket.data.remote.model.user.UserData
import com.example.tezmarket.domain.ProfileRepository
import com.example.tezmarket.utils.Resource
import com.example.tezmarket.utils.SafeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val tezMarketApi: TezMarketApi) : ProfileRepository,
    SafeApiCall() {
    override suspend fun getUser(): Flow<Resource<UserData>> = call {
        tezMarketApi.getUser()
    }
    override suspend fun getAttributesById(attributesId: Int): Flow<Resource<AttributesById>> = call {
        tezMarketApi.getAttributesById(attributesId)
    }

}