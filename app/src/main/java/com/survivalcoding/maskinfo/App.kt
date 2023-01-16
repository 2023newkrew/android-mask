package com.survivalcoding.maskinfo

import android.app.Application
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.survivalcoding.maskinfo.data.data_source.remote.PhotoApi
import com.survivalcoding.maskinfo.data.repository.PhotoRepositoryImpl
import com.survivalcoding.maskinfo.domain.use_case.GetPhotosUseCase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.create

@HiltAndroidApp
class App : Application()