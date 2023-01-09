package com.survivalcoding.maskinfo.data.remote

import com.survivalcoding.maskinfo.data.remote.dto.ResultGetMaskStock
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * MaskStock API communication setup via Retrofit.
 */
interface StoreService {
    @GET("mask")
    suspend fun getResultMaskStock(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("accept") accept: String = "application/json",
    ): ResultGetMaskStock

    @GET("mask")
    suspend fun getAllResultMaskStock(
        @Query("accept") accept: String = "application/json",
    ): ResultGetMaskStock

    companion object {
        private const val BASE_URL = "http://104.198.248.76:3000/"

        fun create(): StoreService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StoreService::class.java)
        }
    }
}
