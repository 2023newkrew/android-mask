package com.survivalcoding.maskinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val infoList = listOf(
        Info("약국1", "주소1", 1f, 1),
        Info("약국2", "주소2", 1f, 5),
        Info("약국3", "주소3", 1f, 10),
        Info("약국4", "주소4", 1f, 50),
        Info("약국5", "주소5", 1f, 100)
    )
    var infoListLiveData = MutableLiveData(infoList)
}

data class Info(val name: String, val address: String, val distance: Float, var stock: Int)