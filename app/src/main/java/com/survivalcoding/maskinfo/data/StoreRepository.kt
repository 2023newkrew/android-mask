package com.survivalcoding.maskinfo

import com.survivalcoding.maskinfo.data.model.ResultGetMaskStock
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MaskApiService {
    @GET("mask")
    fun getInfo(
        @Query("accept") accept: String = "application/json",
    ): Call<ResultGetMaskStock>
}

object RetrofitObject {
    private const val BASE_URL = "http://104.198.248.76:3000/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiService(): MaskApiService {
        return getRetrofit().create(MaskApiService::class.java)
    }
}