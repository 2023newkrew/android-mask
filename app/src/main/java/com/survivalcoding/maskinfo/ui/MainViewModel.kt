package com.survivalcoding.maskinfo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.survivalcoding.maskinfo.Configs.Companion.URL_MASK
import com.survivalcoding.maskinfo.data.Coordinate
import com.survivalcoding.maskinfo.data.Info
import com.survivalcoding.maskinfo.ui.adapter.InfoListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class MainViewModel : ViewModel() {
    private var infoList: ArrayList<Info> = ArrayList()
    var infoListLiveData = MutableLiveData(infoList)
    val infoListAdapter: InfoListAdapter by lazy { InfoListAdapter() }

    var userCoordinate: Coordinate? = null

    fun loadInfoList() {
        CoroutineScope(Dispatchers.IO).launch {
            val conn: HttpURLConnection = withContext(Dispatchers.IO) {
                URL(URL_MASK).openConnection()
            } as HttpURLConnection

            infoList = ArrayList()
            try {
                if (conn.responseCode in 200..300) {
                    val text = conn.inputStream.bufferedReader().use(BufferedReader::readText)
                    val jsonArray = Gson().fromJson(JSONObject(text).get("stores").toString(), JsonArray::class.java)

                    for (jsonElement in jsonArray) {
                        val jsonObject = jsonElement.asJsonObject

                        try {
                            val info = Info(
                                jsonObject.get("name").asString,
                                jsonObject.get("addr").asString,
                                jsonObject.get("remain_stat").asString,
                                Coordinate(jsonObject.get("lat").asDouble, jsonObject.get("lng").asDouble),
                                userCoordinate
                            )
                            if (info.stock != "break") infoList.add(info)
                        } catch (exception: Exception) { // case invalid data
                            when (exception) {
                                is UnsupportedOperationException -> println(exception.stackTrace)
                                is NullPointerException -> println(exception.stackTrace)
                                else -> println(exception.stackTrace)
                            }
                        }
                    }
                }
            } catch (exception: IOException) {
                println(exception.stackTrace) // case IO error
            } finally {
                conn.disconnect()
            }

            infoList.sortBy { it.distance }
            infoListAdapter.submitList(infoList.toMutableList())
            infoListLiveData.postValue(infoList)
        }
    }
}