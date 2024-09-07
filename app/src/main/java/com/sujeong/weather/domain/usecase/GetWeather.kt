package com.sujeong.weather.domain.usecase

import com.sujeong.weather.domain.repository.WeatherRepository
import com.sujeong.weather.domain.util.DataError
import com.sujeong.weather.domain.util.Result
import com.sujeong.weather.domain.weather.WeatherInfo
import javax.inject.Inject

class GetWeather @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(
        lat: Double,
        lng: Double
    ): Result<WeatherInfo, DataError> {
        return weatherRepository.getWeatherData(lat, lng)
    }
}