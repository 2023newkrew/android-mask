package com.survivalcoding.maskinfo.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.survivalcoding.maskinfo.URL_BASE
import com.survivalcoding.maskinfo.domain.repository.InfoRepository
import com.survivalcoding.maskinfo.domain.use_case.GetMaskUseCase
import com.survivalcoding.maskinfo.retrofit.MaskService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideMaskService(): MaskService =
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()
            .create()

    @Provides
    @Singleton
    fun provideGetMaskUseCase(repository: InfoRepository) =
        GetMaskUseCase(repository)
}