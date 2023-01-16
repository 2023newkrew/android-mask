package com.survivalcoding.maskinfo.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.survivalcoding.maskinfo.data.data_source.remote.PhotoApi
import com.survivalcoding.maskinfo.data.repository.PhotoRepositoryImpl
import com.survivalcoding.maskinfo.domain.model.Photo
import com.survivalcoding.maskinfo.domain.repository.PhotoRepository
import com.survivalcoding.maskinfo.domain.use_case.GetPhotosUseCase
import com.survivalcoding.maskinfo.util.MyResult
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePhotoApi(): PhotoApi {
        return Retrofit.Builder()
            .baseUrl(PhotoApi.BASE_URL)
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
            }.asConverterFactory(MediaType.get("application/json")))
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideGetPhotoUseCase(repository: PhotoRepository): GetPhotosUseCase {
        return GetPhotosUseCase(repository)
    }
}