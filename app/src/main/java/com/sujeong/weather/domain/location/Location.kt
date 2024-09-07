package com.sujeong.weather.domain.location

data class Location(
    val lat: Double,
    val lng: Double,
    val address: String? = null
)