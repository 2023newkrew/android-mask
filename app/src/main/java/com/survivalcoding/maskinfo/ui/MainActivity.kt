package com.survivalcoding.maskinfo.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.maskinfo.R
import com.survivalcoding.maskinfo.databinding.ActivityMainBinding
import com.survivalcoding.maskinfo.ui.adapter.MaskStockAdapter

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val maskStockRecyclerView = binding.maskStockRecyclerView
        val maskStockAdapter = MaskStockAdapter(viewModel.dataList)
        maskStockRecyclerView.adapter = maskStockAdapter

        viewModel.dataListLiveData.observe(this) {
            binding.toolbar.title = getString(R.string.title, viewModel.dataList.size)
            maskStockAdapter.notifyDataSetChanged()
        }

        viewModel.loadStores()

    }
}