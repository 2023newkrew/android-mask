package com.survivalcoding.maskinfo.data.datasource

import com.survivalcoding.maskinfo.data.dto.ResultGetMaskStock
import retrofit2.http.GET
import retrofit2.http.Query

interface MaskDataSource {
    @GET("mask")
    suspend fun getAllResultMaskStock(
        @Query("accept") accept: String = "application/json",
    ): ResultGetMaskStock
}
