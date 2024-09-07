package com.sujeong.weather.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherDataDailyDto(
    val time: List<String>,
    @Json(name = "temperature_2m_max")
    val maxTemperatures: List<Double>,
    @Json(name = "weather_code")
    val weatherCodes: List<Int>
)
