package com.sujeong.weather.data.mapper

import com.sujeong.weather.data.remote.WeatherDataDailyDto
import com.sujeong.weather.data.remote.WeatherDataDto
import com.sujeong.weather.data.remote.WeatherDataHourlyDto
import com.sujeong.weather.data.remote.WeatherDto
import com.sujeong.weather.domain.weather.DailyWeatherData
import com.sujeong.weather.domain.weather.WeatherData
import com.sujeong.weather.domain.weather.WeatherInfo
import com.sujeong.weather.domain.weather.WeatherType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

fun WeatherDataHourlyDto.toHourlyWeatherData(): List<WeatherData> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val humidity = humidities[index]
        val apparentTemperature = apparentTemperatures[index]
        val isDay = isDays[index]

        WeatherData(
            time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
            temperatureCelsius = temperature.roundToInt(),
            apparentTemperature = apparentTemperature.roundToInt(),
            windSpeed = windSpeed,
            humidity = humidity.roundToInt(),
            weatherType = WeatherType.toWeatherType(weatherCode, isDay = isDay == 1)
        )
    }
}

fun WeatherDataDailyDto.toDailyWeatherData(): List<DailyWeatherData> {
    return time.mapIndexed { index, time ->
        val temperature = maxTemperatures[index]
        val weatherCode = weatherCodes[index]

        DailyWeatherData(
            date = LocalDate.parse(time, DateTimeFormatter.ISO_DATE),
            temperatureCelsius = temperature.roundToInt(),
            weatherType = WeatherType.toWeatherType(weatherCode)
        )
    }
}

fun WeatherDataDto.toCurrentWeatherData(): WeatherData {
    return WeatherData(
        time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
        temperatureCelsius = temperature.roundToInt(),
        apparentTemperature = apparentTemperature.roundToInt(),
        windSpeed = windSpeed,
        humidity = humidity.roundToInt(),
        weatherType = WeatherType.toWeatherType(weatherCode, isDay = isDay == 1)
    )
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val currentWeatherData = currentWeatherData.toCurrentWeatherData()
    val hourlyWeatherData = hourlyWeatherData.toHourlyWeatherData().filter {
        it.time.isAfter(currentWeatherData.time)
    }.subList(0, 24)
    val dailyWeatherData = dailyWeatherData.toDailyWeatherData().drop(1)

    return WeatherInfo(
        currentWeatherData = currentWeatherData,
        hourlyWeatherData = hourlyWeatherData,
        dailyWeatherData = dailyWeatherData
    )
}