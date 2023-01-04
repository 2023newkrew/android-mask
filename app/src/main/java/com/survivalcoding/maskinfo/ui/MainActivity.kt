package com.survivalcoding.maskinfo.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.maskinfo.*
import com.survivalcoding.maskinfo.data.model.MaskStock
import com.survivalcoding.maskinfo.data.MaskStockStatus
import com.survivalcoding.maskinfo.data.model.ResultGetMaskStock
import com.survivalcoding.maskinfo.ui.adapter.MaskStockAdapter
import com.survivalcoding.maskinfo.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //test
        val dataList = ArrayList<MaskStock>()
        val maskStockRecyclerView = binding.maskStockRecyclerView
        val maskStockAdapter = MaskStockAdapter(dataset = dataList)
        maskStockRecyclerView.adapter = maskStockAdapter
        binding.toolbar.title = getString(R.string.title, dataList.size)

        RetrofitObject.getApiService().getInfo().enqueue(object : Callback<ResultGetMaskStock> {
            // api 호출 성공시
            override fun onResponse(call: Call<ResultGetMaskStock>, response: Response<ResultGetMaskStock>) {
                for (data in response.body()?.stores ?: emptyList()) {
                    dataList.add(MaskStock(data.name, data.addr, 5000, MaskStockStatus.Sufficient))
                }
                maskStockAdapter.notifyDataSetChanged()
                binding.toolbar.title = getString(R.string.title, dataList.size)
            }

            // api 호출 실패시
            override fun onFailure(call: Call<ResultGetMaskStock>, t: Throwable) {
                Log.e("retrofit onFailure", "$t")
            }
        })
    }
}