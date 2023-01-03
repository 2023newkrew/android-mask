package com.survivalcoding.maskinfo

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.survivalcoding.maskinfo.Configs.Companion.URL_MASK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.lang.Math.*
import java.net.HttpURLConnection
import java.net.URL

class MainViewModel : ViewModel() {
    var userCoordinate: Coordinate? = null
    var infoList = ArrayList<Info>()
    var infoListLiveData = MutableLiveData(infoList)
    val recyclerAdapter: RecyclerAdapter by lazy { RecyclerAdapter() }

    fun loadInfoList(firstTime: Boolean) {
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
                                Coordinate(jsonObject.get("lat").asDouble, jsonObject.get("lng").asDouble)
                            )
                            if (info.stock != "break") infoList.add(info)
                        } catch (exception: Exception) {
                            println(exception.stackTrace) // case invalid data
                        }
                    }
                }
            } catch (exception: Exception) {
                println(exception.stackTrace)
            } finally {
                conn.disconnect()
            }

            infoList.sortBy { it.distance }
            if (firstTime) recyclerAdapter.notifyItemRangeInserted(0, infoList.size)
            else recyclerAdapter.notifyItemRangeChanged(0, infoList.size)
            infoListLiveData.postValue(infoList)
        }
    }

    inner class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {
        inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
            val addressTextView: TextView = itemView.findViewById(R.id.address_text_view)
            val distanceTextView: TextView = itemView.findViewById(R.id.distance_text_view)
            val enoughTextView: TextView = itemView.findViewById(R.id.enough_text_view)
            val stockTextView: TextView = itemView.findViewById(R.id.stock_text_view)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            return RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_info, parent, false))
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.nameTextView.text = infoList[position].name
            holder.addressTextView.text = infoList[position].address
            holder.distanceTextView.text = "${infoList[position].distance}km"
            when (infoList[position].stock) {
                "few" -> {
                    holder.enoughTextView.text = "소진 임박"
                    holder.stockTextView.text = "30개 미만"
                    holder.enoughTextView.setTextColor(Color.RED)
                    holder.stockTextView.setTextColor(Color.RED)
                }
                "some" -> {
                    holder.enoughTextView.text = "여유"
                    holder.stockTextView.text = "30개 이상"
                    holder.enoughTextView.setTextColor(Color.YELLOW)
                    holder.stockTextView.setTextColor(Color.YELLOW)
                }
                "plenty" -> {
                    holder.enoughTextView.text = "충분"
                    holder.stockTextView.text = "100개 이상"
                    holder.enoughTextView.setTextColor(Color.GREEN)
                    holder.stockTextView.setTextColor(Color.GREEN)
                }
            }
        }

        override fun getItemCount(): Int {
            return infoList.size
        }
    }

    inner class Info(
        val name: String,
        val address: String,
        var stock: String,
        private val coordinate: Coordinate
    ) {
        val distance: Double by lazy {
            userCoordinate?.let {
                calcDistance(coordinate, it)
            } ?: 0.0
        }

        private fun calcDistance(a: Coordinate, b: Coordinate): Double {
            val theta = a.longitude - b.longitude
            var distance = kotlin.math.sin(convertDegreeToRadius(a.latitude)) * kotlin.math.sin(convertDegreeToRadius(b.latitude)) +
                    kotlin.math.cos(convertDegreeToRadius(a.latitude)) * kotlin.math.cos(convertDegreeToRadius(b.latitude)) * kotlin.math.cos(convertDegreeToRadius(theta))
            distance = kotlin.math.acos(distance)
            distance = convertRadiusToDegree(distance)
            distance *= 60 * 1.1515
            distance *= 1.609344 // to km
            return distance
        }

        private fun convertDegreeToRadius(degree: Double): Double {
            return degree * PI / 180.0
        }

        private fun convertRadiusToDegree(radius: Double): Double {
            return radius * 180.0 / PI
        }
    }
}

data class Coordinate(
    val latitude: Double,
    val longitude: Double
)