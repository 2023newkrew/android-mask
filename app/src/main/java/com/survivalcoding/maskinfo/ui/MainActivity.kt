package com.survivalcoding.maskinfo.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.survivalcoding.maskinfo.MaskInfoApplication
import com.survivalcoding.maskinfo.R
import com.survivalcoding.maskinfo.databinding.ActivityMainBinding
import com.survivalcoding.maskinfo.ui.adapter.StoreAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(this.application as MaskInfoApplication)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val maskStockRecyclerView = binding.maskStockRecyclerView
        val maskStockAdapter = StoreAdapter()
        maskStockRecyclerView.adapter = maskStockAdapter

        lifecycleScope.launch {
            viewModel.pagingStores.collectLatest {
                (maskStockRecyclerView.adapter as StoreAdapter).submitData(it)
            }
        }
        val items = viewModel.pagingStores
        // Collect from the PagingData Flow in the ViewModel, and submit it to the
        // PagingDataAdapter.
        lifecycleScope.launch {
            // We repeat on the STARTED lifecycle because an Activity may be PAUSED
            // but still visible on the screen, for example in a multi window app
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                items.collectLatest {
                    maskStockAdapter.submitData(it)
                    // 타이틀 갱신안되는 이슈
                    // TODO
                    binding.toolbar.title = getString(R.string.title, maskStockAdapter.itemCount)
                }
            }

        }

        // Use the CombinedLoadStates provided by the loadStateFlow on the ArticleAdapter to
        // show progress bars when more data is being fetched
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                maskStockAdapter.loadStateFlow.collect {
                    binding.prependProgress.isVisible = it.source.prepend is LoadState.Loading
                    binding.appendProgress.isVisible = it.source.append is LoadState.Loading
                }
            }
        }

    }
}