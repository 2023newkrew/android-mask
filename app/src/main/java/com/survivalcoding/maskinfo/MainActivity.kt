package com.survivalcoding.maskinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.survivalcoding.maskinfo.ui.MainViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.state.observe(this) { state ->
            if (state.isLoading) {
                // progress on
            } else {
                // progress off
            }

            println("photos : ${state.photos}")
        }
    }
}