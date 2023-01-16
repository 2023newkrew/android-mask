package com.survivalcoding.maskinfo.presentation

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.survivalcoding.maskinfo.App
import com.survivalcoding.maskinfo.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contents = findViewById<TextView>(R.id.contents_text_view)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collectLatest { state ->
                        if (state.isLoading) {
                            contents.text = "로딩 중"
                        } else {
                            // progress off
                            contents.text = state.photos.toString()
                        }

                        println("MainActivity photos : ${state.photos}")
                    }
                }

                launch {
                    viewModel.event.collect { event ->
                        when (event) {
                            MainUiEvent.DataLoadSuccess -> println("MainActivity : 로드 성공")
                            is MainUiEvent.ShowSnackBar -> {
                                println("MainActivity 스넥바 : ${event.message}")
                            }
                        }
                    }
                }
            }
        }

    }
}