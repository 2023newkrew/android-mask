package com.survivalcoding.maskinfo.data.repository

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.survivalcoding.maskinfo.domain.model.Permission
import com.survivalcoding.maskinfo.domain.repository.PermissionChecker

class LocationPermissionChecker(
    private val activity: AppCompatActivity,
) : PermissionChecker {

    private val requestPermissionLauncher =
        activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) &&
                        permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {

                }
                else -> {
                    // No location access granted.
                }
            }
        }

    override suspend fun isServiceEnabled(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun checkPermission(): Permission {
        when {
            ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(
                        activity,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED -> {
                return Permission.Always
            }

            ActivityCompat.shouldShowRequestPermissionRationale(activity, )

            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.REQUESTED_PERMISSION
                )
            }
        }
    }

    override suspend fun requestPermission(): Permission {
        TODO("Not yet implemented")
    }
}