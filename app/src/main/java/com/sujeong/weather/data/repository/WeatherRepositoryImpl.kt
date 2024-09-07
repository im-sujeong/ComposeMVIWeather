package com.sujeong.weather.data.repository

import android.util.Log
import com.sujeong.weather.data.mapper.toWeatherInfo
import com.sujeong.weather.data.remote.WeatherApi
import com.sujeong.weather.domain.repository.WeatherRepository
import com.sujeong.weather.domain.util.DataError
import com.sujeong.weather.domain.util.Result
import com.sujeong.weather.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {
    override suspend fun getWeatherData(
        lat: Double, lng: Double
    ): Result<WeatherInfo, DataError> {
        return try {
            Result.Success(
                data = api.getWeatherData(
                    lat = lat,
                    lng = lng
                ).toWeatherInfo()
            )
        }catch (e: Exception) {
            Log.e("sujeongTAG", "getWeatherData $e")
            Result.Error(DataError.Network.SERVER_ERROR)
        }
    }
}