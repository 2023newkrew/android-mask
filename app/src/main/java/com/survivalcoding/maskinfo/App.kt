package com.survivalcoding.maskinfo

import android.app.Application
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.survivalcoding.maskinfo.data.data_source.remote.PhotoApi
import com.survivalcoding.maskinfo.data.repository.PhotoRepositoryImpl
import com.survivalcoding.maskinfo.domain.use_case.GetPhotosUseCase
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.create

class App : Application() {
    val api: PhotoApi = Retrofit.Builder()
        .baseUrl(PhotoApi.BASE_URL)
        .addConverterFactory(Json {
            ignoreUnknownKeys = true
        }.asConverterFactory(MediaType.get("application/json")))
        .build()
        .create()

    val photoRepository by lazy {
        PhotoRepositoryImpl(api)
    }

    val getPhotoUseCase by lazy {
        GetPhotosUseCase(photoRepository)
    }
}