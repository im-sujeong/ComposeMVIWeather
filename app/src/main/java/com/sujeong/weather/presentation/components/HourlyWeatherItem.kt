package com.sujeong.weather.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sujeong.weather.domain.weather.WeatherData
import com.sujeong.weather.domain.weather.WeatherType
import com.sujeong.weather.presentation.ui.theme.Gray
import com.sujeong.weather.presentation.ui.theme.Typography
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HourlyWeatherItem(
    weatherData: WeatherData
) {
    val formattedTime = remember(weatherData) {
        weatherData.time.format(
            DateTimeFormatter.ofPattern("HH:mm")
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = weatherData.temperatureCelsius.toString() + "℃",
            style = Typography.bodySmall.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Icon(
            painter = painterResource(id = weatherData.weatherType.iconRes),
            contentDescription = ""
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = formattedTime,
            style = Typography.bodySmall,
            color = Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HourlyWeatherItemPreview() {
    HourlyWeatherItem(
        weatherData = WeatherData(
            time = LocalDateTime.now(),
            temperatureCelsius = 20,
            apparentTemperature = 22,
            windSpeed = 11.0,
            humidity = 15,
            weatherType = WeatherType.Snow
        )
    )
}
