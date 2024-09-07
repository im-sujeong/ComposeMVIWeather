package com.sujeong.weather.domain.weather

import java.time.LocalDate

data class DailyWeatherData(
    val date: LocalDate,
    val temperatureCelsius: Int,
    val weatherType: WeatherType,
)
