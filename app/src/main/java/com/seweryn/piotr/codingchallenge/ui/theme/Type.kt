package com.seweryn.piotr.codingchallenge.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.seweryn.piotr.codingchallenge.ui.font.Font

// Set of Material typography styles to start with
val Typography = Typography(
  bodyLarge = TextStyle(
    fontFamily = Font.roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 20.sp,
  ),
  labelLarge = TextStyle(
    fontFamily = Font.roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
  ),
  labelMedium = TextStyle(
    fontFamily = Font.roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
  ),
  titleMedium = TextStyle(
    fontFamily = Font.roboto,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
  ),
  /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)