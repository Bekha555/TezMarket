package com.example.tezmarket.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

open class MoshiConverter {
    companion object {
        val moshi: Moshi = Moshi.Builder().build()

        inline fun <reified T> objectToJsonString(obj: T): String? {
            val adapter: JsonAdapter<T> = moshi.adapter(T::class.java)

            return try {
                adapter.toJson(obj)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        inline fun <reified T> jsonStringToObject(jsonString: String): T? {
            val adapter: JsonAdapter<T> = moshi.adapter(T::class.java)

            return try {
                adapter.fromJson(jsonString)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }


    }

}