package com.example.tezmarket.presentation

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun ProductShimmer() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        AnimateProductShimmer()
    }
}

@Composable
fun AnimateProductShimmer() {
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
    Product(brush)
}

@Composable
fun Product(brush: Brush) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .height(420.dp)
                    .width(280.dp)
                    .padding(end = 5.dp)
                    .background(brush)
            )
            Box(
                modifier = Modifier
                    .height(420.dp)
                    .width(200.dp)
                    .background(brush)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "", fontSize = 24.sp, modifier = Modifier
                    .width(150.dp)
                    .background(brush, shape = RoundedCornerShape(4.dp))
            )
            Text(
                text = "", fontSize = 24.sp, modifier = Modifier
                    .width(80.dp)
                    .background(brush, shape = RoundedCornerShape(4.dp))
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(top = 5.dp)
                .padding(horizontal = 10.dp)
        ) {
            Text(
                text = "",
                fontSize = 12.sp,
                modifier = Modifier
                    .width(70.dp)
                    .background(brush, shape = RoundedCornerShape(4.dp))
                    .align(Alignment.TopStart)
            )
            Text(
                text = "",
                fontSize = 13.sp,
                modifier = Modifier
                    .width(90.dp)
                    .background(brush, shape = RoundedCornerShape(4.dp))
                    .align(Alignment.BottomStart)
            )
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .background(brush, shape = CircleShape)
                    .align(Alignment.CenterEnd)
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 40.dp)
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .height(10.dp)
                .background(brush, shape = RoundedCornerShape(4.dp))
        )
        repeat(15) {
        Box(
            modifier = Modifier
                .padding(top = 5.dp)
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .height(10.dp)
                .background(brush, shape = RoundedCornerShape(4.dp))
        )
    }


    }
}