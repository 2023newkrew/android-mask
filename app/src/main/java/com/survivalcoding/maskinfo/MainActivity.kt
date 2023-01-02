package com.survivalcoding.maskinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.maskinfo.adapter.MaskStockAdapter
import com.survivalcoding.maskinfo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dummyList = ArrayList<MaskStock>()
        dummyList.add(MaskStock("판교 아지트", "성남시 분당구", 5000, 150))
        dummyList.add(MaskStock("판교 아지트", "성남시 분당구", 5000, 29))
        dummyList.add(MaskStock("판교 아지트", "성남시 분당구", 5000, 30))

        val maskStockRecyclerView = binding.maskStockRecyclerView
        val maskStockAdapter = MaskStockAdapter(this, dataset = dummyList)
        maskStockRecyclerView.adapter = maskStockAdapter
        val linearLayoutManager = LinearLayoutManager(this)
        maskStockRecyclerView.layoutManager = linearLayoutManager

    }
}