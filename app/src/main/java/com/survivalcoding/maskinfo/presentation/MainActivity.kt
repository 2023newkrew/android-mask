package com.survivalcoding.maskinfo.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.survivalcoding.maskinfo.App
import com.survivalcoding.maskinfo.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory(application as App)
    }

    class MainViewModelFactory(private val application: App) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {

                @Suppress("UNCHECKED_CAST")
                return MainViewModel(application.getPhotoUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest { state ->
                    if (state.isLoading) {
                        // progress on
                    } else {
                        // progress off
                    }

                    println("photos : ${state.photos}")
                }
            }
        }

    }
}