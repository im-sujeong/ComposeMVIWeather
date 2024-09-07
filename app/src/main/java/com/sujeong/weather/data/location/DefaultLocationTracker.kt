package com.sujeong.weather.data.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.os.Build
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.sujeong.weather.domain.location.Location
import com.sujeong.weather.domain.location.LocationTracker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DefaultLocationTracker @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
): LocationTracker {
    override suspend fun getCurrentLocation(): Location? {
        val hasAccessFindLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if(!hasAccessFindLocationPermission || !hasAccessCoarseLocationPermission || !isGpsEnabled) {
            return null
        }

        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                if(isComplete) {
                    if(isSuccessful) {
                        cont.resume(
                            Location(
                                lat = result.latitude,
                                lng = result.longitude
                            )
                        )
                    }else {
                        cont.resume(null)
                    }

                    return@suspendCancellableCoroutine
                }

                addOnSuccessListener {
                    cont.resume(
                        Location(
                            lat = result.latitude,
                            lng = result.longitude
                        )
                    )
                }

                addOnFailureListener {
                    cont.resume(null)
                }

                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }

    override suspend fun getCurrentAddress(
        lat: Double,
        lng: Double
    ): String? = withContext(Dispatchers.IO) {
        try {
            suspendCoroutine { cont ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    Geocoder(application, Locale.getDefault())
                        .getFromLocation(lat, lng, 1) {
                            cont.resume(it.firstOrNull()?.thoroughfare)
                        }
                }else {
                    @Suppress("DEPRECATION")
                    val address = Geocoder(application, Locale.getDefault())
                        .getFromLocation(
                            lat, lng, 1
                        )?.firstOrNull()?.thoroughfare

                    cont.resume(address)
                }
            }
        }catch (e: Exception) {
            null
        }
    }
}