package com.survivalcoding.maskinfo.data.di

import com.survivalcoding.maskinfo.data.repository.InfoRepositoryImpl
import com.survivalcoding.maskinfo.domain.repository.InfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindInfoRepository(infoRepositoryImpl: InfoRepositoryImpl): InfoRepository
}