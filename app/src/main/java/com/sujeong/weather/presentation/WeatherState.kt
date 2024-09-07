package com.sujeong.weather.presentation

import com.sujeong.weather.domain.weather.WeatherInfo
import com.sujeong.weather.presentation.util.UiText

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val address: String? = null,
    val isLoading: Boolean = true,
    val error: UiText? = null
)