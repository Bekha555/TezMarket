package com.example.tezmarket.data.repositoryimpl

import com.example.tezmarket.data.remote.TezMarketApi
import com.example.tezmarket.data.remote.model.addadvertisement.AddAdvertisement
import com.example.tezmarket.data.remote.model.addadvertisement.Attribute
import com.example.tezmarket.data.remote.model.deleteAdvertisement.DeleteAdvertisementData
import com.example.tezmarket.data.remote.model.myadvertisements.MyAdvertisementsData
import com.example.tezmarket.data.remote.model.uploadFiles.UploadFiles
import com.example.tezmarket.domain.AdvertisementRepository
import com.example.tezmarket.utils.Resource
import com.example.tezmarket.utils.SafeApiCall
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

class AdvertisementRepositoryImpl @Inject constructor(private val tezMarketApi: TezMarketApi) :
    AdvertisementRepository, SafeApiCall() {
    override suspend fun getMyAdvertisements(): Flow<Resource<MyAdvertisementsData>> = call {
        tezMarketApi.getMyAdvertisements()
    }

    override suspend fun addAdvertisement(bodyData: Map<String, Any>): Flow<Resource<AddAdvertisement>> =
        call {
            tezMarketApi.addAdvertisement(bodyData)
        }

    override suspend fun deleteAddvertisement(advertisement_id: Int): Flow<Resource<DeleteAdvertisementData>> =
        call {
            tezMarketApi.deleteAddvertisement(advertisement_id)
        }

    override suspend fun uploadFile(file: MultipartBody.Part): Flow<Resource<UploadFiles>> = call {
        tezMarketApi.uploadFile(file)
    }


}