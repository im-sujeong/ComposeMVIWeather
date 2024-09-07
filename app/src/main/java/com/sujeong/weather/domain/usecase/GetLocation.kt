package com.sujeong.weather.domain.usecase

import com.sujeong.weather.domain.location.Location
import com.sujeong.weather.domain.location.LocationTracker
import javax.inject.Inject

class GetLocation @Inject constructor(
    private val locationTracker: LocationTracker
) {
    suspend operator fun invoke(): Location? {
        return locationTracker.getCurrentLocation()?.let {
            return it.copy(
                lat = it.lat,
                lng = it.lng,
                address = locationTracker.getCurrentAddress(it.lat, it.lng)
            )
        }
    }
}