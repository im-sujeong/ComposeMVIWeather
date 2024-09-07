package com.sujeong.weather.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sujeong.weather.R

val weatherFontFamily = FontFamily(
    Font(R.font.suit_thin, FontWeight.Thin),
    Font(R.font.suit_extra_light, FontWeight.ExtraLight),
    Font(R.font.suit_regular, FontWeight.Normal),
    Font(R.font.suit_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = weatherFontFamily,
        fontWeight = FontWeight.Thin,
        fontSize = 140.sp,
        color = Color.Black
    ),

    titleLarge = TextStyle(
        fontFamily = weatherFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        color = Color.Black
    ),

    bodyLarge = TextStyle(
        fontFamily = weatherFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.Black
    ),

    bodySmall = TextStyle(
        fontFamily = weatherFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = Color.Black
    )

    /* Other default text styles to override
    ,
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)