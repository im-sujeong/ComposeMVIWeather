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
import com.sujeong.weather.domain.weather.DailyWeatherData
import com.sujeong.weather.domain.weather.WeatherType
import com.sujeong.weather.presentation.WeatherState
import com.sujeong.weather.presentation.ui.theme.Typography
import java.time.LocalDate

@Composable
fun DailyWeather(
    state: WeatherState
) {
    state.weatherInfo?.dailyWeatherData?.let {
        DailyWeather(
            dailyWeatherData = it
        )
    }
}

@Composable
fun DailyWeather(
    dailyWeatherData: List<DailyWeatherData>
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.weather_data_daily),
            style = Typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items(dailyWeatherData.size) { index ->
                DailyWeatherItem(dailyWeatherData = dailyWeatherData[index])
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DailyWeatherPreview() {
    DailyWeather(
        dailyWeatherData = listOf(
            DailyWeatherData(
                date = LocalDate.now(),
                temperatureCelsius = 31,
                weatherType = WeatherType.ClearSky
            ), DailyWeatherData(
                date = LocalDate.now(),
                temperatureCelsius = 31,
                weatherType = WeatherType.ClearSky
            ), DailyWeatherData(
                date = LocalDate.now(),
                temperatureCelsius = 31,
                weatherType = WeatherType.ClearSky
            )
        )
    )
}