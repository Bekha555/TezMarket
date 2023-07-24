package com.example.tezmarket.data.repositoryimpl

import com.example.tezmarket.data.remote.TezMarketApi
import com.example.tezmarket.data.remote.model.addadvertisement.AddAdvertisement
import com.example.tezmarket.data.remote.model.myadvertisements.MyAdvertisementsData
import com.example.tezmarket.data.remote.model.uploadFiles.UploadFiles
import com.example.tezmarket.domain.AdvertisementRepository
import com.example.tezmarket.utils.Resource
import com.example.tezmarket.utils.SafeApiCall
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class AdvertisementRepositoryImpl @Inject constructor(private val tezMarketApi: TezMarketApi) :
    AdvertisementRepository, SafeApiCall() {
    override suspend fun getMyAdvertisements(): Flow<Resource<MyAdvertisementsData>> = call {
        tezMarketApi.getMyAdvertisements()
    }

    override suspend fun addAdvertisement(
        title: String,
        price: Int,
        category_id: Int,
        description: String,
        images: String,
        attribute_id: Int
    ): Flow<Resource<AddAdvertisement>> = call {
        tezMarketApi.addAdvertisement(title, price, category_id, description, images, attribute_id)
    }

    override suspend fun uploadFile(file: File): Flow<Resource<UploadFiles>> = call {
        tezMarketApi.uploadFile(file)
    }


}