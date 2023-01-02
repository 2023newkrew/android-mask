package com.survivalcoding.maskinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.survivalcoding.maskinfo.Configs.Companion.URL_MASK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

class MainViewModel : ViewModel() {
    val infoList = listOf(
        Info("약국1", "주소1", 1f, 1),
        Info("약국2", "주소2", 1f, 5),
        Info("약국3", "주소3", 1f, 10),
        Info("약국4", "주소4", 1f, 50),
        Info("약국5", "주소5", 1f, 100)
    )
    var infoListLiveData = MutableLiveData(infoList)

    fun loadInfoList() {
        CoroutineScope(Dispatchers.IO).launch {
            val conn: HttpURLConnection = withContext(Dispatchers.IO) {
                URL(URL_MASK).openConnection()
            } as HttpURLConnection

            try {
                if (conn.responseCode in 200..300) {
                    val text = conn.inputStream.bufferedReader().use(BufferedReader::readText)
                    val jsonArray = Gson().fromJson(JSONObject(text).get("stores").toString(), JsonArray::class.java)

                    for(jsonElement in jsonArray){
                        val jsonObject = jsonElement.asJsonObject
                        println()
                    }
//                    for (jsonElement in jsonArray) {
//                        val jsonObject = jsonElement.asJsonObject
//
//                        val alley = Alley()
//                        alley.id = jsonObject.get("OPENDATA_ID").asString
//                        alley.name = jsonObject.get("FD_CS").asString
//                        alley.contents = jsonObject.get("SMPL_DESC").asString
//
//                        // case exception
//                        if (alley.name!!.contains("2030"))
//                            continue
//
//                        // initialize image link
//                        imageSearchController.searchImageLink(alley.links, alley.name!!, 1)
//
//                        if (alley.links[0] != "") {
//                            // unify style
//                            alley.unityStyle()
//
//                            // add alley
//                            alleys.add(alley)
//                        }
//                    }

                }
            } catch (exception: Exception) {
                println(exception.stackTrace)
            } finally {
                conn.disconnect()
            }
        }
    }
}

data class Info(val name: String, val address: String, val distance: Float, var stock: Int)