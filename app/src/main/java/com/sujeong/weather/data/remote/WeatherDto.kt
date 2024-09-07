package com.sujeong.weather.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherDto(
    @Json(name = "current")
    val currentWeatherData: WeatherDataDto,
    @Json(name = "hourly")
    val hourlyWeatherData: WeatherDataHourlyDto,
    @Json(name = "daily")
    val dailyWeatherData: WeatherDataDailyDto
)
