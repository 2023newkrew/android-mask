package com.survivalcoding.maskinfo.di

import com.survivalcoding.maskinfo.data.remote.StoreService
import com.survivalcoding.maskinfo.data.repository.StoreRepositoryImpl
import com.survivalcoding.maskinfo.domain.repository.StoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MaskStockAppModule {

    @Provides
    @Singleton
    fun provideStoreService(): StoreService {
        return StoreService.create()
    }


    @Provides
    @Singleton
    fun provideStoreRepository(storeService: StoreService): StoreRepository {
        return StoreRepositoryImpl(storeService)
    }
}