package com.example.tezmarket.data.repositoryimpl

import androidx.paging.PagingSource
import com.example.tezmarket.data.remote.TezMarketApi
import com.example.tezmarket.data.remote.model.AdressPagingSource
import com.example.tezmarket.data.remote.model.adress.AddAdress
import com.example.tezmarket.data.remote.model.adress.AdressById
import com.example.tezmarket.data.remote.model.adress.AdressesData
import com.example.tezmarket.data.remote.model.adress.Data
import com.example.tezmarket.data.remote.model.adress.DataX
import com.example.tezmarket.data.remote.model.adress.UpdateAdress
import com.example.tezmarket.domain.AdressRepository
import com.example.tezmarket.utils.Resource
import com.example.tezmarket.utils.SafeApiCall
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class AdressRepositoryImpl @Inject constructor(private val tezMarketApi: TezMarketApi) :
    AdressRepository,
    SafeApiCall() {

    override fun getAllAdresses(): PagingSource<Int, DataX> = AdressPagingSource(tezMarketApi)

    override suspend fun getAdresses(): Flow<Resource<AdressesData>> = call {
        tezMarketApi.getAllAdresses(page = 1)
    }

    override suspend fun addAdress(
        adressName: String,
        cityId: Int,
        regionName: String,
        zipCode: Int
    ): Flow<Resource<AddAdress>> = call {
        tezMarketApi.addAdress(adressName, cityId, regionName, zipCode)
    }

    override suspend fun updateAdress(
        addressId: Int,
        adressName: String,
        cityId: Int,
        regionName: String,
        zipCode: Int
    ): Flow<Resource<UpdateAdress>> = call {
        tezMarketApi.updateAdress(addressId, adressName, cityId, regionName, zipCode)
    }

    override suspend fun getAddressById(addressId: Int): Flow<Resource<AdressById>> = call {
        tezMarketApi.getAddressById(addressId)
    }

}