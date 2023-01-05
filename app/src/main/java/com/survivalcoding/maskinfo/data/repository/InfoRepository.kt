package com.survivalcoding.maskinfo.data.repository

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.survivalcoding.maskinfo.Configs.Companion.URL_BASE
import com.survivalcoding.maskinfo.retrofit.MaskService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.create

class InfoRepository {
    @OptIn(ExperimentalSerializationApi::class)
    private val maskService: MaskService by lazy {
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()
            .create()
    }

    suspend fun getMask(currentPage: Int) = maskService.getMask(currentPage, 20)
}