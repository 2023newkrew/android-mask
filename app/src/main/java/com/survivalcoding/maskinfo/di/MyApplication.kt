package com.survivalcoding.maskinfo.di

import android.app.Application
import com.survivalcoding.maskinfo.di.component.ApplicationComponent
import com.survivalcoding.maskinfo.di.component.DaggerApplicationComponent

class MyApplication : Application() {
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
}