package com.example.tezmarket.domain

import com.example.tezmarket.data.remote.model.addadvertisement.AddAdvertisement
import com.example.tezmarket.data.remote.model.myadvertisements.MyAdvertisementsData
import com.example.tezmarket.data.remote.model.uploadFiles.UploadFiles
import com.example.tezmarket.utils.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File

interface AdvertisementRepository {
    suspend fun getMyAdvertisements(): Flow<Resource<MyAdvertisementsData>>
    suspend fun addAdvertisement(
        title: String,
        price: Int,
        category_id: Int,
        description: String,
        images: String,
        attribute_id: Int
    ): Flow<Resource<AddAdvertisement>>

    suspend fun uploadFile(file: File): Flow<Resource<UploadFiles>>

}