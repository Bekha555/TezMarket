package com.example.tezmarket.domain

import com.example.tezmarket.data.remote.model.addadvertisement.AddAdvertisement
import com.example.tezmarket.data.remote.model.addadvertisement.Attribute
import com.example.tezmarket.data.remote.model.deleteAdvertisement.DeleteAdvertisementData
import com.example.tezmarket.data.remote.model.myadvertisements.MyAdvertisementsData
import com.example.tezmarket.data.remote.model.uploadFiles.UploadFiles
import com.example.tezmarket.utils.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody


interface AdvertisementRepository {
    suspend fun getMyAdvertisements(): Flow<Resource<MyAdvertisementsData>>
    suspend fun addAdvertisement(
        bodyData: Map<String, Any>
    ): Flow<Resource<AddAdvertisement>>

    suspend fun deleteAddvertisement(advertisement_id: Int): Flow<Resource<DeleteAdvertisementData>>

    suspend fun uploadFile(file: MultipartBody.Part): Flow<Resource<UploadFiles>>

}