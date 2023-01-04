package com.survivalcoding.maskinfo.data.repository

import com.survivalcoding.maskinfo.Configs
import com.survivalcoding.maskinfo.data.model.Coordinate
import com.survivalcoding.maskinfo.data.model.Info
import org.json.JSONObject
import org.junit.Assert
import org.junit.Test
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

class InfoRepositoryTest {

    @Test
    fun getInfoList() {
        val infoList = ArrayList<Info>()
        val currentPage = 1
        val userCoordinate = Coordinate(0.0, 0.0)

        val conn: HttpURLConnection = URL(Configs.URL_MASK + "?page=${currentPage}&limit=20").openConnection() as HttpURLConnection

        if (conn.responseCode in 200..300) {
            val text = conn.inputStream.bufferedReader().use(BufferedReader::readText)

            // test limit
            val count = JSONObject(text).getInt("count")
            Assert.assertEquals(count, 20)

            val jsonArray = JSONObject(text).getJSONArray("stores")

            // test length
            val length = jsonArray.length()
            Assert.assertEquals(length, 20)

            // test first data
            val name = jsonArray.getJSONObject(0).getString("name")
            Assert.assertEquals(name, "선경약국")

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