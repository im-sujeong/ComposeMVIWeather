package com.sujeong.weather.domain.weather

import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Int,
    val apparentTemperature: Int,
    val windSpeed: Double,
    val humidity: Int,
    val weatherType: WeatherType,
)
