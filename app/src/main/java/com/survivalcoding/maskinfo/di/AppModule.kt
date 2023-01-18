package com.survivalcoding.maskinfo.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.survivalcoding.maskinfo.Configs
import com.survivalcoding.maskinfo.data.data_source.api.MaskService
import com.survivalcoding.maskinfo.data.repository.InfoRepositoryImpl
import com.survivalcoding.maskinfo.domain.repository.InfoRepository
import com.survivalcoding.maskinfo.domain.use_case.GetInfoUseCase
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

    @Provides
    @Singleton
    fun provideMaskService(): MaskService {
        @OptIn(ExperimentalSerializationApi::class)
        return Retrofit.Builder()
            .baseUrl(Configs.URL_BASE)
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideInfoRepository(maskService: MaskService):InfoRepository {
        return InfoRepositoryImpl(maskService)
    }

    @Provides
    @Singleton
    fun provideGetInfoUseCase(repository: InfoRepository): GetInfoUseCase {
        return GetInfoUseCase(repository)
    }
}