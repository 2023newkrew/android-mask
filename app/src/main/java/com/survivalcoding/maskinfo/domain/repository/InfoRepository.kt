package com.survivalcoding.maskinfo.domain.repository

import com.survivalcoding.maskinfo.data.dto.Mask

interface InfoRepository {
    suspend fun getMask(currentPage: Int): Mask
}