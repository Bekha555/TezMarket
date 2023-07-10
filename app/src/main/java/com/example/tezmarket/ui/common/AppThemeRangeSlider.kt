@file:OptIn(ExperimentalMaterialApi::class)
@file:Suppress("UNUSED_EXPRESSION")

package com.example.tezmarket.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tezmarket.R
import com.example.tezmarket.ui.theme.Gray
import com.example.tezmarket.ui.theme.Primary

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppThemeRangeSlider() {

    var sliderValues by remember {
        mutableStateOf(100f..400f) // На каких значениях должен стоять ползунок
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .background(Transparent)
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
        ) {
            Text(
                text = "Ценовой диапазон",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = FontFamily(
                    Font(R.font.metropolis_bold)
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 10.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(White)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
            ) {
                Text(
                    text = "${sliderValues.start.toInt()}TJS", fontSize = 14.sp
                )
                Text(
                    text = "${sliderValues.endInclusive.toInt()}TJS", fontSize = 14.sp
                )
            }
            RangeSlider(
                value = sliderValues, onValueChange = { sliderValues_ ->
                    sliderValues = sliderValues_
                }, valueRange = 1f..500f,
//            onValueChangeFinished = {
//                // Когда пользователь закончит действие
//
//            },
                colors = SliderDefaults.colors(
                    thumbColor = Primary, activeTrackColor = Primary, inactiveTrackColor = Gray
                )
            )
        }
    }
}