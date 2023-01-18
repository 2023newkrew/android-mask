package com.survivalcoding.maskinfo.domain.model

data class StoreInfo(
    val name: String,
    val address: String,
    var stock: String,
    private val coordinate: Coordinate,
    private val userCoordinate: Coordinate?
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
        return degree * Math.PI / 180.0
    }

    private fun convertRadiusToDegree(radius: Double): Double {
        return radius * 180.0 / Math.PI
    }
}