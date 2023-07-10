package com.example.tezmarket.domain

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.tezmarket.data.remote.model.adress.AddAdress
import com.example.tezmarket.data.remote.model.adress.AdressById
import com.example.tezmarket.data.remote.model.adress.AdressesData
import com.example.tezmarket.data.remote.model.adress.Data
import com.example.tezmarket.data.remote.model.adress.DataX
import com.example.tezmarket.data.remote.model.adress.UpdateAdress
import com.example.tezmarket.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AdressRepository {

    fun getAllAdresses(): PagingSource<Int, DataX>

    suspend fun getAdresses(): Flow<Resource<AdressesData>>

    suspend fun addAdress(
        adressName: String,
        cityId: Int,
        regionName: String,
        zipCode: Int
    ): Flow<Resource<AddAdress>>

    suspend fun updateAdress(
        addressId: Int,
        adressName: String,
        cityId: Int,
        regionName: String,
        zipCode: Int
    ): Flow<Resource<UpdateAdress>>

    suspend fun getAddressById(addressId: Int): Flow<Resource<AdressById>>
}