package com.example.tezmarket.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tezmarket.R
import com.example.tezmarket.ui.theme.Shadow


@Composable
fun TopText(text: String, color: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = color)
    ) {
        Text(
            text = text,
            fontSize = 34.sp,
            fontFamily = FontFamily(
                Font(R.font.metropolis_bold)
            ),
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(bottom = 3.dp)
                .width(300.dp)
        )
    }
}


@Composable
fun AppThemeTopText(text: String, color: Color, shadow: Boolean, modifier: Modifier) {
    val colorStops = arrayOf(
        0.0f to Shadow,
        1f to Color.Transparent
    )

    Column {
        TopText(text = text, color = color)
        if (shadow)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(20.dp)
                    .background(Brush.verticalGradient(colorStops = colorStops))
            )
    }
}

@Preview(name = "AppThemeTextPreview")
@Composable
fun DefaultPreview1() {
    AppThemeTopText("Регистрация", color = White, shadow = false, modifier = Modifier)
}