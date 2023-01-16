package com.survivalcoding.maskinfo.domain.repository

import com.survivalcoding.maskinfo.domain.model.Permission

interface PermissionChecker {
    suspend fun isServiceEnabled(): Boolean
    suspend fun checkPermission(): Permission
    suspend fun requestPermission(): Permission
}