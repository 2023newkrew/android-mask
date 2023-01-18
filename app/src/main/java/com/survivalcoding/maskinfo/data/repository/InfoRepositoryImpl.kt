package com.survivalcoding.maskinfo.data.repository

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.survivalcoding.maskinfo.URL_BASE
import com.survivalcoding.maskinfo.domain.repository.InfoRepository
import com.survivalcoding.maskinfo.retrofit.MaskService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class InfoRepositoryImpl @Inject constructor(private val maskService: MaskService) :
    InfoRepository {
    override suspend fun getMask(currentPage: Int) = maskService.getMask(currentPage, 20)
}