package com.seweryn.piotr.codingchallenge.ui.font

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.seweryn.piotr.codingchallenge.R

object Font {
  val roboto: FontFamily
    get() = FontFamily(
      Font(
        R.font.roboto_bold,
        FontWeight.Bold,
      ),
      Font(
        R.font.roboto_regular,
        FontWeight.Normal,
      ),
    )
}