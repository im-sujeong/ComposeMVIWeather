package com.sujeong.weather.domain.repository

import com.sujeong.weather.domain.util.DataError
import com.sujeong.weather.domain.util.Result
import com.sujeong.weather.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, lng: Double): Result<WeatherInfo, DataError>
}