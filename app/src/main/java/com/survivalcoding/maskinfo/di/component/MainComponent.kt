package com.survivalcoding.maskinfo.di.component

import com.survivalcoding.maskinfo.di.scope.ActivityScope
import com.survivalcoding.maskinfo.presentation.view.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
}