package com.example.tezmarket.data.remote.model.uploadFiles


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UploadFiles(
    @Json(name = "filePath")
    val filePath: String? = ""
)