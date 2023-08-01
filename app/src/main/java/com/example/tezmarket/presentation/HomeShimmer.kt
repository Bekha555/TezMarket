package com.example.tezmarket.presentation

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tezmarket.ui.theme.Primary

@Preview(showBackground = true)
@Composable
fun HomeShimmer() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        AnimateHomeShimmer()
    }
}

@Composable
fun AnimateHomeShimmer() {
    val shimmerColors = listOf(
        Primary.copy(0.2f),
        Primary.copy(0.1f),
        Primary.copy(0.2f)
    )
    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing
            ), RepeatMode.Reverse
        )
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(10f, 10f),
        end = Offset(translateAnimation.value, translateAnimation.value)
    )
    Home(brush)
}

@Composable
fun Home(brush: Brush) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(state = rememberScrollState())) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(
                    brush
                )
        )
    //    repeat(2) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .padding(horizontal = 20.dp)
            ) {
                Column(verticalArrangement = Arrangement.Center, modifier = Modifier) {
                    Text(
                        text = "",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .width(120.dp)
                            .background(brush, shape = RoundedCornerShape(4.dp))
                    )
                    Text(
                        text = "", fontSize = 12.sp, modifier = Modifier
                            .width(100.dp)
                            .padding(top = 6.dp)
                            .background(brush, shape = RoundedCornerShape(4.dp))
                    )
                }
                Text(
                    text = "", fontSize = 12.sp,
                    modifier = Modifier
                        .width(50.dp)
                        .background(brush, shape = RoundedCornerShape(4.dp))
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    //.padding(horizontal = 25.dp)
                    .horizontalScroll(state = rememberScrollState())
            ) {
                repeat(4) {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.Start, modifier = Modifier.padding(start = 20.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .height(200.dp)
                                .width(150.dp)
                                .background(brush, shape = RoundedCornerShape(15.dp))
                        )
                        Text(
                            text = "",
                            fontSize = 12.sp,
                            modifier = Modifier
                                .width(110.dp)
                                .padding(top = 8.dp, start = 8.dp)
                                .background(brush, shape = RoundedCornerShape(4.dp))
                        )
                    }
                }
            }
    //    }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
                .padding(horizontal = 20.dp)
        ) {
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = "",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .width(180.dp)
                        .background(brush, shape = RoundedCornerShape(4.dp))
                )
                Text(
                    text = "", fontSize = 12.sp, modifier = Modifier
                        .width(100.dp)
                        .padding(top = 6.dp)
                        .background(brush, shape = RoundedCornerShape(4.dp))
                )
            }

            Text(
                text = "", fontSize = 12.sp,
                modifier = Modifier
                    .width(50.dp)
                    .background(brush, shape = RoundedCornerShape(4.dp))
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                //.padding(horizontal = 25.dp)

        ) {
            repeat(4) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(start = 20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .height(200.dp)
                            .width(150.dp)
                            .background(brush, shape = RoundedCornerShape(15.dp))
                    )
                    Text(
                        text = "",
                        fontSize = 12.sp,
                        modifier = Modifier
                            .width(110.dp)
                            .padding(top = 8.dp, start = 8.dp)
                            .background(brush, shape = RoundedCornerShape(4.dp))
                    )
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
                .padding(horizontal = 20.dp)
        ) {
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier) {
                Text(
                    text = "",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .width(120.dp)
                        .background(brush, shape = RoundedCornerShape(4.dp))
                )
                Text(
                    text = "", fontSize = 12.sp, modifier = Modifier
                        .width(100.dp)
                        .padding(top = 6.dp)
                        .background(brush, shape = RoundedCornerShape(4.dp))
                )
            }
            Text(
                text = "", fontSize = 12.sp,
                modifier = Modifier
                    .width(50.dp)
                    .background(brush, shape = RoundedCornerShape(4.dp))
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                //.padding(horizontal = 25.dp)
                .horizontalScroll(state = rememberScrollState())
        ) {
            repeat(4) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start, modifier = Modifier.padding(start = 20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .height(200.dp)
                            .width(150.dp)
                            .background(brush, shape = RoundedCornerShape(15.dp))
                    )
                    Text(
                        text = "",
                        fontSize = 12.sp,
                        modifier = Modifier
                            .width(110.dp)
                            .padding(top = 8.dp, start = 8.dp)
                            .background(brush, shape = RoundedCornerShape(4.dp))
                    )
                }
            }
        }
    }
}