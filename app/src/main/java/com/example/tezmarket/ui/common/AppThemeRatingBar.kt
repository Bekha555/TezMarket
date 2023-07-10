package com.example.tezmarket.common.other

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tezmarket.R

@SuppressLint("UnrememberedMutableState")
@Composable
fun AppThemeRatingBar(
    modifier: Modifier = Modifier, rating: Int
) {
    var ratingState by remember { mutableStateOf(rating) }
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Какова ваша оценка?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(
                Font(R.font.metropolis_bold)
            ),
            modifier = Modifier.padding(bottom = 17.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (i in 1..5) {
                Image(painter = painterResource(
                    id = if (i > ratingState) {
                        R.drawable.star
                    } else R.drawable.star_fill
                ),
                    contentDescription = "start",
                    modifier = Modifier
                        .padding(horizontal = 14.dp)
                        .width(37.dp)
                        .height(37.dp)
                        .clickable { ratingState = i })
            }
        }
        Text(
            text = "Пожалуйста, поделитесь\n" + "своим мнением о продукте",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(
                Font(R.font.metropolis_bold)
            ), textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 33.dp)
        )
    }
}


@Preview(name = "AppThemeRatingBar")
@Composable
fun DefaultPreview1() {
    AppThemeRatingBar(modifier = Modifier, 0)
}