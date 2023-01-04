package com.survivalcoding.maskinfo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.maskinfo.data.model.Coordinate
import com.survivalcoding.maskinfo.data.model.Info
import com.survivalcoding.maskinfo.data.repository.InfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import java.io.IOException

class MainViewModel : ViewModel() {
    private val infoRepository: InfoRepository by lazy { InfoRepository() }
    private var infoList: ArrayList<Info> = ArrayList()
    var infoListLiveData = MutableLiveData(infoList)

    var userCoordinate: Coordinate? = null
    private var currentPage = 1

    fun loadInfoList(initialize: Boolean) {
        if (initialize) {
            infoList = ArrayList()
            currentPage = 1
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                infoRepository.getInfoList(infoList, currentPage++, userCoordinate)
            } catch (exception: Exception) {
                when (exception) { // case invalid data
                    is ClassCastException -> println(exception.stackTrace)
                    is JSONException -> println(exception.stackTrace)
                    is IOException -> println(exception.stackTrace)
                    else -> println(exception.stackTrace)
                }
            }
            infoListLiveData.postValue(infoList)
        }
    }
}