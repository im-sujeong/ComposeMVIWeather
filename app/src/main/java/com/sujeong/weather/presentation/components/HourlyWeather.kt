package com.sujeong.weather.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sujeong.weather.R
import com.sujeong.weather.domain.weather.WeatherData
import com.sujeong.weather.domain.weather.WeatherType
import com.sujeong.weather.presentation.WeatherState
import com.sujeong.weather.presentation.ui.theme.Typography
import java.time.LocalDateTime

@Composable
fun HourlyWeather(
    state: WeatherState
) {
    state.weatherInfo?.hourlyWeatherData?.let {
        HourlyWeather(
            hourlyWeatherData = it
        )
    }
}

@Composable
fun HourlyWeather(
    hourlyWeatherData: List<WeatherData>
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.weather_data_hourly),
            style = Typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            items(hourlyWeatherData.size) { index ->
                HourlyWeatherItem(weatherData = hourlyWeatherData[index])
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HourlyWeatherPreview() {
    HourlyWeather(
        hourlyWeatherData = listOf(
            WeatherData(
                time = LocalDateTime.now(),
                temperatureCelsius = 31,
                apparentTemperature = 33,
                windSpeed = 10.0,
                humidity = 10,
                weatherType = WeatherType.ClearSky
            ), WeatherData(
                time = LocalDateTime.now(),
                temperatureCelsius = 31,
                apparentTemperature = 33,
                windSpeed = 10.0,
                humidity = 10,
                weatherType = WeatherType.ClearSky
            ), WeatherData(
                time = LocalDateTime.now(),
                temperatureCelsius = 31,
                apparentTemperature = 33,
                windSpeed = 10.0,
                humidity = 10,
                weatherType = WeatherType.ClearSky
            ), WeatherData(
                time = LocalDateTime.now(),
                temperatureCelsius = 31,
                apparentTemperature = 33,
                windSpeed = 10.0,
                humidity = 10,
                weatherType = WeatherType.ClearSky
            )
        )
    )
}