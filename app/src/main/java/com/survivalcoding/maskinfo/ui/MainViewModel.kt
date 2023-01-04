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
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class MainViewModel : ViewModel() {
    val dataList = ArrayList<MaskStock>()
    val dataListLiveData = MutableLiveData<List<MaskStock>>()
    var myLat: Double = 37.394940490775 //판교역 위도
    var myLng: Double = 127.110105738

    fun loadStores() {
        dataList.clear()
        RetrofitObject.getApiService().getInfo().enqueue(object : Callback<ResultGetMaskStock> {
            // api 호출 성공시
            override fun onResponse(
                call: Call<ResultGetMaskStock>,
                response: Response<ResultGetMaskStock>
            ) {

                for (data in response.body()?.stores ?: emptyList()) {
                    if (data.name != null && data.addr != null && data.lat != null
                        && data.lng != null && data.remain_stat != null
                    ) {
                        dataList.add(
                            MaskStock(
                                data.name,
                                data.addr,
                                calculateDistance(data.lat, data.lng),
                                when (data.remain_stat) {
                                    "plenty" -> MaskStockStatus.Sufficient
                                    "break" -> MaskStockStatus.InSufficient
                                    "some" -> MaskStockStatus.Spare
                                    else -> MaskStockStatus.InSufficient //TODO 예외처리
                                }
                            )
                        )
                    }
                }
                dataListLiveData.postValue(dataList)
            }

            // api 호출 실패시
            override fun onFailure(call: Call<ResultGetMaskStock>, t: Throwable) {
                Log.e("retrofit onFailure", "$t")
            }
        })
    }

    fun calculateDistance(
        lat: Double,
        lng: Double
    ): Double {
        val earthRadius = 6371000.0 //meters
        val dLat = Math.toRadians((lat - myLat))
        val dLng = Math.toRadians((lng - myLng))
        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(myLat)) * cos(
            Math.toRadians(lat)
        ) *
                sin(dLng / 2) * sin(dLng / 2)
        val c =
            2 * atan2(sqrt(a), sqrt(1 - a))
        return (earthRadius * c)
    }
}