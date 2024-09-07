package com.sujeong.weather.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherDataHourlyDto(
    val time: List<String>,
    @Json(name = "temperature_2m")
    val temperatures: List<Double>,
    @Json(name = "weather_code")
    val weatherCodes: List<Int>,
    @Json(name = "wind_speed_10m")
    val windSpeeds: List<Double>,
    @Json(name = "relative_humidity_2m")
    val humidities: List<Double>,
    @Json(name = "apparent_temperature")
    val apparentTemperatures: List<Double>,
    @Json(name = "is_day")
    val isDays: List<Int>
)
