package com.sujeong.weather.domain.location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?

    suspend fun getCurrentAddress(
        lat: Double,
        lng: Double
    ): String?
}