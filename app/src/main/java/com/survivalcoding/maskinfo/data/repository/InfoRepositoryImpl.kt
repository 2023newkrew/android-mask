package com.survivalcoding.maskinfo.data.repository

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.survivalcoding.maskinfo.Configs.Companion.URL_BASE
import com.survivalcoding.maskinfo.data.data_source.api.MaskService
import com.survivalcoding.maskinfo.data.data_source.mapper.toStoreInfo
import com.survivalcoding.maskinfo.domain.model.Coordinate
import com.survivalcoding.maskinfo.domain.model.StoreInfo
import com.survivalcoding.maskinfo.domain.repository.InfoRepository
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.create

class InfoRepositoryImpl(private val maskService: MaskService): InfoRepository {
    override suspend fun getMask(currentPage: Int, userCoordinate: Coordinate?): List<StoreInfo?> {
        val response = maskService.getMask(currentPage, 20)

        return if (response.isSuccessful) {
            val storeInfos = response.body()?.stores?.map { it.toStoreInfo(userCoordinate) } ?: emptyList()
            storeInfos
        } else {
            // TODO: 결과 담는 클래스 구현해야함
            emptyList()
        }
    }
}