package com.survivalcoding.maskinfo.data.repository

import com.survivalcoding.maskinfo.Configs.Companion.URL_MASK
import com.survivalcoding.maskinfo.data.model.Coordinate
import com.survivalcoding.maskinfo.data.model.Info
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

class InfoRepository {
    suspend fun getInfoList(
        infoList: ArrayList<Info>,
        currentPage: Int,
        userCoordinate: Coordinate?
    ) {
        val conn: HttpURLConnection = withContext(Dispatchers.IO) {
            URL(URL_MASK + "?page=${currentPage}&limit=20").openConnection()
        } as HttpURLConnection

        if (conn.responseCode in 200..300) {
            val text = conn.inputStream.bufferedReader().use(BufferedReader::readText)
            val jsonArray = JSONObject(text).getJSONArray("stores")
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val info = Info(
                    jsonObject.getString("name"),
                    jsonObject.getString("addr"),
                    jsonObject.getString("remain_stat"),
                    Coordinate(jsonObject.getDouble("lat"), jsonObject.getDouble("lng")),
                    userCoordinate
                )
                if (info.stock != "break" &&
                    info.stock != "empty" &&
                    info.name != "null" &&
                    info.address != "null" &&
                    info.stock != "null"
                ) infoList.add(info)
            }
        }
        conn.disconnect()

        infoList.sortBy { it.distance }
    }
}