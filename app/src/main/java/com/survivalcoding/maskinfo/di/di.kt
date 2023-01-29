package com.survivalcoding.maskinfo.di

import android.app.Application
import com.survivalcoding.maskinfo.presentation.view.MainActivity
import dagger.Component

// Definition of the Application graph
@Component
interface ApplicationComponent {
    // This tells Dagger that Activity requests injection so the graph needs to
    // satisfy all the dependencies of the fields that LoginActivity is requesting.
    fun inject(activity: MainActivity)
}

// appComponent lives in the Application class to share its lifecycle
class MyApplication: Application() {
    // Reference to the application graph that is used across the whole app
    val appComponent = DaggerApplicationComponent.create()
}