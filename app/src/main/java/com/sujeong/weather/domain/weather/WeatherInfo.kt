package com.sujeong.weather.domain.weather

data class WeatherInfo(
    val currentWeatherData: WeatherData,
    val hourlyWeatherData: List<WeatherData>,
    val dailyWeatherData: List<DailyWeatherData>
)
