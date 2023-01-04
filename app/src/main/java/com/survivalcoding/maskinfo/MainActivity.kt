package com.survivalcoding.maskinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.maskinfo.adapter.MaskStockAdapter
import com.survivalcoding.maskinfo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dummyList = ArrayList<MaskStock>()
        dummyList.add(MaskStock("판교 아지트", "성남시 분당구 판교로 판교역 동편 테스트", 5000, MaskStockStatus.Sufficient))
        dummyList.add(MaskStock("판교 아지트", "성남시 분당구", 5000, MaskStockStatus.InSufficient))
        dummyList.add(MaskStock("판교 아지트", "성남시 분당구", 5000, MaskStockStatus.Spare))

        val maskStockRecyclerView = binding.maskStockRecyclerView
        val maskStockAdapter = MaskStockAdapter( dataset = dummyList)
        maskStockRecyclerView.adapter = maskStockAdapter

        binding.toolbar.title = getString(R.string.title, dummyList.size)
    }
}