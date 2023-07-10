package com.example.tezmarket.ui.common

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tezmarket.R
import com.example.tezmarket.ui.theme.Primary

@SuppressLint("SuspiciousIndentation")
@Composable
fun AnimatedButtons(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    isCart: Boolean,
    favoriteToggle: () -> Unit,
    cartAdd: () -> Unit,
    cartDel: () -> Unit
) {
    var showButtons by remember { mutableStateOf(false) }
    var selected by remember {
        mutableStateOf(0)
    }
    var heart by remember { if (isFavorite) mutableStateOf(1) else mutableStateOf(0) }
    var market by remember {if (isCart) mutableStateOf(1) else mutableStateOf(0) }
    val isButtonPressed = remember { mutableStateOf(false) }


    val button1Offset by animateDpAsState(
        targetValue = if (showButtons) 0.dp else 80.dp,
        animationSpec = tween(durationMillis = 650)
    )

    val button2Offset by animateDpAsState(
        targetValue = if (showButtons) 0.dp else 40.dp,
        animationSpec = tween(durationMillis = 650)
    )


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (showButtons) {
            IconButton(
                modifier = Modifier
                    .offset(y = button1Offset)
                    .padding(bottom = 2.dp)
                    .size(40.dp)
                    .shadow(elevation = 4.dp, clip = true, shape = CircleShape)
                    .clip(CircleShape)
                    .background(color = Color.White),
                onClick = {
                    if (heart == 0) {
                        heart = 1
                    } else {
                        heart = 0
                    }
                    favoriteToggle()
                }
            ) {
                Image(
                    painter = painterResource(
                        id = if (heart == 0) {
                            R.drawable.heart_icon
                        } else {
                            R.drawable.filled_heart_icon
                        }
                    ),
                    contentDescription = "heart",
                    modifier = Modifier
                        .size(15.dp)

                )
            }

            IconButton(
                modifier = Modifier
                    .offset(y = button2Offset)
                    .padding(bottom = 2.dp)
                    .size(40.dp)
                    .shadow(elevation = 4.dp, clip = true, shape = CircleShape)
                    .background(color = Color.White)
                    .clip(CircleShape),
                onClick = {
                    if (market == 0) {
                        market = 1
                        cartAdd()
                    } else {
                        market = 0
                        cartDel()
                    }
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.markets_img),
                    contentDescription = "heart",
                    tint = (if (market == 0) {
                        Color.Gray
                    } else {
                        Primary
                    }),
                    modifier = Modifier
                        .size(15.dp)

                )
            }
        }
        IconButton(
            onClick = {
                showButtons = !showButtons
                if (selected == 0) {
                    selected = 1
                } else {
                    selected = 0
                }
                isButtonPressed.value = !isButtonPressed.value
            }, modifier = Modifier
                .size(40.dp)
                .shadow(elevation = 4.dp, clip = true, shape = CircleShape)
                .clip(CircleShape)
                .background(color = Color.White)

        ) {
            Icon(
                painter = painterResource(R.drawable.plus_icon),
                tint = (if (selected == 0) {
                    Color.Gray
                } else {
                    Primary
                }),
                contentDescription = "heart",
                modifier = Modifier
                    .size(15.dp)

            )
        }


    }

}

