package com.survivalcoding.maskinfo.di.component

import com.survivalcoding.maskinfo.di.module.MainComponentsModule
import com.survivalcoding.maskinfo.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainComponentsModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun mainComponent(): MainComponent.Factory
}