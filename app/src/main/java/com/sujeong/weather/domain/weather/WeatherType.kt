package com.sujeong.weather.domain.weather

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.sujeong.weather.R

sealed class WeatherType(
    @StringRes val weatherDesc: Int,
    @DrawableRes val iconRes: Int
) {
    data object ClearSky: WeatherType(
        weatherDesc = R.string.weather_desc_clear_sky,
        iconRes = R.drawable.ic_sunny
    )

    data object ClearSkyNight: WeatherType(
        weatherDesc = R.string.weather_desc_clear_sky,
        iconRes = R.drawable.ic_night_clear
    )

    data object PartlyCloudy: WeatherType(
        weatherDesc = R.string.weather_desc_partly_cloudy,
        iconRes = R.drawable.ic_partly_cloudy
    )

    data object PartlyCloudyNight: WeatherType(
        weatherDesc = R.string.weather_desc_partly_cloudy,
        iconRes = R.drawable.ic_night_overcast
    )

    data object Overcast: WeatherType(
        weatherDesc = R.string.weather_desc_overcast,
        iconRes = R.drawable.ic_cloud
    )

    data object Foggy: WeatherType(
        weatherDesc = R.string.weather_desc_foggy,
        iconRes = R.drawable.ic_foggy
    )

    data object Drizzle: WeatherType(
        weatherDesc = R.string.weahter_desc_drizzle,
        iconRes = R.drawable.ic_drizzle
    )

    data object FreezingDrizzle: WeatherType(
        weatherDesc = R.string.weather_desc_freezing_drizzle,
        iconRes = R.drawable.ic_freezing_drizzle
    )

    data object Rain: WeatherType(
        weatherDesc = R.string.weather_desc_rain,
        iconRes = R.drawable.ic_rainy
    )

    data object RainShower: WeatherType(
        weatherDesc = R.string.weather_desc_rain_shower,
        iconRes = R.drawable.ic_rainy
    )

    data object Snow: WeatherType(
        weatherDesc = R.string.weather_desc_snow,
        iconRes = R.drawable.ic_snowing
    )

    data object Thunderstorm: WeatherType(
        weatherDesc = R.string.weather_desc_thunderstorm,
        iconRes = R.drawable.ic_thunderstorm
    )

    data object Hail: WeatherType(
        weatherDesc = R.string.weather_desc_hail,
        iconRes = R.drawable.ic_hail
    )

    companion object {
        fun toWeatherType(code: Int, isDay: Boolean = true): WeatherType {
            return when(code) {
                0, 1 -> if(isDay) {
                    ClearSky
                }else {
                    ClearSkyNight

                }
                2 -> if(isDay) {
                    PartlyCloudy
                }else {
                    PartlyCloudyNight
                }
                3 -> Overcast
                45, 48 -> Foggy
                51, 53, 55 -> Drizzle
                56, 57 -> FreezingDrizzle
                61, 63, 65 -> Rain
                66, 67, 71, 73, 75, 77, 85, 86 -> Snow
                80, 81, 82 -> RainShower
                95 -> Thunderstorm
                96, 99 -> Hail
                else -> ClearSky
            }
        }
    }
}