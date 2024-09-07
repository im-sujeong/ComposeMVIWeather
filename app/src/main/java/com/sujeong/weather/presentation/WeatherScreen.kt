package com.sujeong.weather.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.sujeong.weather.R
import com.sujeong.weather.presentation.components.CurrentWeatherItem
import com.sujeong.weather.presentation.components.DailyWeather
import com.sujeong.weather.presentation.components.HourlyWeather
import com.sujeong.weather.presentation.ui.theme.Typography

@RequiresPermission(
    anyOf = [
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    ]
)
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        val allPermissionsGranted = results.all { it.value }
        if (results.isNotEmpty() && allPermissionsGranted) {
            viewModel.loadWeatherInfo()
        }
    }

    if(hasLocationPermission(context)) {
        viewModel.loadWeatherInfo()
    }

    val state = viewModel.state

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        if(!hasLocationPermission(context)) {
            requestPermissionLauncher.launch(permissions)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if(state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }else {
                state.error?.let {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_error),
                            contentDescription = ""
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = it.asString(),
                            style = Typography.bodyLarge,
                        )
                    }
                } ?: kotlin.run {
                    Column(
                        modifier = Modifier
                            .padding(
                                top = 30.dp,
                                start = 30.dp,
                                end = 30.dp
                            )
                            .verticalScroll(rememberScrollState())
                    ) {
                        CurrentWeatherItem(state = state)

                        Spacer(modifier = Modifier.height(30.dp))

                        HorizontalDivider(
                            thickness = 3.dp,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        HourlyWeather(state = state)

                        Spacer(modifier = Modifier.height(30.dp))

                        HorizontalDivider(
                            thickness = 3.dp,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        DailyWeather(state = state)
                    }
                }
            }
        }
    }
}

private fun hasLocationPermission(
    context: Context
): Boolean{
    val hasAccessFindLocationPermission = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

    return hasAccessFindLocationPermission && hasAccessCoarseLocationPermission && isGpsEnabled
}