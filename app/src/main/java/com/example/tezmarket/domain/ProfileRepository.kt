package com.example.tezmarket.domain

import com.example.tezmarket.data.remote.model.AttributesByCategory.AttributesById
import com.example.tezmarket.data.remote.model.addadvertisement.AddAdvertisement
import com.example.tezmarket.data.remote.model.myadvertisements.MyAdvertisementsData
import com.example.tezmarket.data.remote.model.user.UserData
import com.example.tezmarket.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun getUser(): Flow<Resource<UserData>>
    suspend fun getAttributesById(attributesId: Int): Flow<Resource<AttributesById>>


}