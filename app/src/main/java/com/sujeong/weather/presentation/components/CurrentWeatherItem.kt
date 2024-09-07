package com.sujeong.weather.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sujeong.weather.R
import com.sujeong.weather.domain.weather.WeatherData
import com.sujeong.weather.domain.weather.WeatherType
import com.sujeong.weather.presentation.WeatherState
import com.sujeong.weather.presentation.ui.theme.Gray
import com.sujeong.weather.presentation.ui.theme.Typography
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun CurrentWeatherItem(
    state: WeatherState,
) {
    state.weatherInfo?.currentWeatherData?.let { weatherData ->
        CurrentWeatherItem(
            address = state.address,
            currentWeatherData = weatherData
        )
    }
}

@Composable
fun CurrentWeatherItem(
    address: String?,
    currentWeatherData: WeatherData,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = address ?: "",
                    style = Typography.titleLarge
                )

                Spacer(modifier = Modifier.width(2.dp))

                Icon(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = ""
                )
            }

            Text(
                text = currentWeatherData.time.format(
                    DateTimeFormatter.ofPattern("MM월 dd일, E")
                ),
                style = Typography.titleLarge.copy(
                    color = Gray
                ),
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }

        Spacer(modifier = Modifier.height(66.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = currentWeatherData.temperatureCelsius.toString(),
                style = Typography.displayLarge
            )

            Icon(
                modifier = Modifier
                    .size(66.dp)
                    .padding(
                        top = 34.dp,
                        start = 8.dp
                    ),
                painter = painterResource(
                    id = currentWeatherData.weatherType.iconRes
                ),
                contentDescription = "",
            )
        }

        Text(
            text = stringResource(
                id = currentWeatherData.weatherType.weatherDesc
            ),
            style = Typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = stringResource(
                id = R.string.apparent_temperature,
                currentWeatherData.apparentTemperature
            ),
            style = Typography.bodyLarge.copy(
                fontWeight = FontWeight.Thin
            )
        )

        Spacer(modifier = Modifier.height(68.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            WeatherDataDisplay(
                value = currentWeatherData.humidity.toString(),
                unit = "%",
                icon = ImageVector.vectorResource(id = R.drawable.ic_water_drop),
            )

            WeatherDataDisplay(
                value = currentWeatherData.windSpeed.toString(),
                unit = "km/h",
                icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFE8E8E8
)
@Composable
fun CurrentWeatherItemPreview() {
    CurrentWeatherItem(
        address = "가산동",
        WeatherData(
            time = LocalDateTime.now(),
            temperatureCelsius = 20,
            apparentTemperature = 22,
            windSpeed = 11.0,
            humidity = 15,
            weatherType = WeatherType.Overcast
        )
    )
}