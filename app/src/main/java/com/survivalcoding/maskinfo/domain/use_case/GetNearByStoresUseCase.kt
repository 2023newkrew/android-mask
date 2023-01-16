package com.survivalcoding.maskinfo.domain.use_case

import com.survivalcoding.maskinfo.domain.model.Location
import com.survivalcoding.maskinfo.domain.model.Permission
import com.survivalcoding.maskinfo.domain.model.Store
import com.survivalcoding.maskinfo.domain.repository.LocationRepository
import com.survivalcoding.maskinfo.domain.repository.PermissionChecker
import com.survivalcoding.maskinfo.domain.repository.StoreRepository

class GetNearByStoresUseCase(
    private val storeRepository: StoreRepository,
    private val locationRepository: LocationRepository,
    private val permissionChecker: PermissionChecker,
) {
    suspend operator fun invoke(): List<Store> {
        val stores = storeRepository.getStores()

        val serviceEnabled = permissionChecker.isServiceEnabled()

        if (serviceEnabled) {
            var permission = permissionChecker.checkPermission()

            when (permission) {
                Permission.Denied -> {
                    permission = permissionChecker.requestPermission()

                    if (permission == Permission.Denied) {
                        println("거부")
                        return stores
                    }
                }
                Permission.DeniedForever -> {
                    println("2번 거부")
                    return stores
                }
                Permission.Always -> {
                    val location = locationRepository.getLocation()

                    return stores.map { store ->
                        store.copy(
                            distance = locationRepository.distanceBetween(
                                store.location,
                                Location(location.latitude, location.longitude),
                            )
                        )
                    }.sortedBy { it.distance }
                }
            }
        }
        return stores
    }
}