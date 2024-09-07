package com.sujeong.weather.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sujeong.weather.R
import com.sujeong.weather.presentation.ui.theme.Typography

@Composable
fun WeatherDataDisplay(
    value: String,
    unit: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = ""
        )

        Spacer(modifier = Modifier.width(2.dp))

        Text(
            text = "$value $unit",
            style = Typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherDataDisplayPreview() {
    WeatherDataDisplay(
        value = "10",
        unit = "km/h",
        icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
        modifier = Modifier
    )
}