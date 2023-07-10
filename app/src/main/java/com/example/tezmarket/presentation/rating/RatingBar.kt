package com.example.tezmarket.presentation.rating

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tezmarket.R

@SuppressLint("UnrememberedMutableState")
@Composable
fun ConstantRatingBar(
    modifier: Modifier = Modifier, startRating: Int
) {
    var ratingState by remember { mutableStateOf(startRating) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            for (i in 5 downTo 1) {
                Image(
                    painter = painterResource(
                        id = if (i > ratingState) {
                            R.drawable.white_image
                        } else {
                            R.drawable.star_fill
                        }
                    ),
                    contentDescription = "start",
                    modifier = modifier
                )
            }
        }
    }
}


@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    text: String,
    startRating: Int,
    selectable: Boolean,
    ratingSelected: (Int) -> Unit
) {
    var ratingState by remember { mutableStateOf(startRating) }
    val interactionSource = remember { MutableInteractionSource() }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            for (i in 1..5) {
                Image(
                    painter = painterResource(
                        id = if (i > ratingState) {
                            R.drawable.star
                        } else {
                            R.drawable.star_fill
                        }
                    ),
                    contentDescription = "start",
                    modifier = modifier
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            if (selectable) {
                                ratingState = i
                                ratingSelected(i)
                            }
                        }
                )
            }
                Text(
                    text = if (text.isEmpty()) {"0"} else {"($text)"},
                    color = Color.Gray,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 2.dp)
                )

        }
    }
}


@Preview(name = "AppThemeRatingBar")
@Composable
fun DefaultPreview1() {
    ConstantRatingBar(modifier = Modifier, startRating = 4)
}