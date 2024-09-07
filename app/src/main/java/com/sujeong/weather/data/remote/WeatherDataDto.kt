package com.sujeong.weather.data.remote

import com.squareup.moshi.Json

data class WeatherDataDto(
    val time: String,
    @Json(name = "temperature_2m")
    val temperature: Double,
    @Json(name = "relative_humidity_2m")
    val humidity: Double,
    @Json(name = "apparent_temperature")
    val apparentTemperature: Double,
    @Json(name = "is_day")
    val isDay: Int,
    @Json(name = "weather_code")
    val weatherCode: Int,
    @Json(name = "wind_speed_10m")
    val windSpeed: Double
)
