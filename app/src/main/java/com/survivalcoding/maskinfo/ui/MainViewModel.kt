package com.survivalcoding.maskinfo.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.survivalcoding.maskinfo.RetrofitObject
import com.survivalcoding.maskinfo.data.model.MaskStock
import com.survivalcoding.maskinfo.data.model.MaskStockStatus
import com.survivalcoding.maskinfo.data.model.ResultGetMaskStock
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    val dataList = ArrayList<MaskStock>()
    val dataListLiveData = MutableLiveData<List<MaskStock>>()

    fun loadStores() {
        dataList.clear()
        RetrofitObject.getApiService().getInfo().enqueue(object : Callback<ResultGetMaskStock> {
            // api 호출 성공시
            override fun onResponse(
                call: Call<ResultGetMaskStock>,
                response: Response<ResultGetMaskStock>
            ) {
                for (data in response.body()?.stores ?: emptyList()) {
                    dataList.add(MaskStock(data.name, data.addr, 5000, MaskStockStatus.Sufficient))
                }
                dataListLiveData.postValue(dataList)
            }

            // api 호출 실패시
            override fun onFailure(call: Call<ResultGetMaskStock>, t: Throwable) {
                Log.e("retrofit onFailure", "$t")
            }
        })
    }
}