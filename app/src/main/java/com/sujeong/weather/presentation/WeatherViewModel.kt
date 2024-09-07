package com.sujeong.weather.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sujeong.weather.R
import com.sujeong.weather.domain.usecase.GetLocation
import com.sujeong.weather.domain.usecase.GetWeather
import com.sujeong.weather.domain.util.Result
import com.sujeong.weather.presentation.util.UiText
import com.sujeong.weather.presentation.util.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeather: GetWeather,
    private val getLocation: GetLocation
): ViewModel(){
    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            if(state.weatherInfo != null || state.error != null) {
                return@launch
            }

            state = state.copy(
                isLoading = true,
                error = null
            )

            getLocation()?.let { location ->
                when(val result = getWeather(location.lat, location.lng)) {
                    is Result.Success -> {
                        state = state.copy(
                            weatherInfo = result.data,
                            address = location.address,
                            isLoading = false,
                            error = null
                        )
                    }

                    is Result.Error -> {
                        state = state.copy(
                            weatherInfo = null,
                            address = location.address,
                            isLoading = false,
                            error = result.error.asUiText()
                        )
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = UiText.StringResource(R.string.error_location_permission)
                )
            }
        }
    }
}