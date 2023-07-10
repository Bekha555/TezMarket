package com.example.tezmarket.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tezmarket.R
import com.example.tezmarket.ui.theme.Gray

@Composable
fun OrderDetailCard() {
    Box(
        modifier = Modifier
            .padding(top = 15.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .fillMaxWidth()
                .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
                .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(110.dp),
                painter = painterResource(id = R.drawable.product_list_image),
                contentDescription = null
            )
            Column(
                modifier = Modifier.padding(
                    start = 10.dp,
                    top = 10.dp,
                    bottom = 10.dp,
                    end = 10.dp
                ), verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Text(
                    text = "Пуловер",
                    fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                    fontSize = 16.sp
                )
                Text(
                    text = "Mango",
                    fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                    fontSize = 11.sp
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(13.dp),
                ) {
                    Row {
                        Text(text = "Цвет:", fontSize = 11.sp, color = Gray)
                        Text(text = "Синий", fontSize = 11.sp)
                    }
                    Row {
                        Text(text = "Размер:", fontSize = 11.sp, color = Gray)
                        Text(text = "L", fontSize = 11.sp)
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row {
                        Text(text = "Количество:", fontSize = 11.sp, color = Gray)
                        Text(text = "1", fontSize = 11.sp)
                    }
                    Text(text = "311TJS", fontFamily = FontFamily(Font(R.font.metropolis_bold)))
                }
            }
        }
    }
}


@Composable
fun OrderDetailsItems(itemName: String, itemInfo: String) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$itemName:",
            color = Gray,
            fontFamily = FontFamily(Font(R.font.metropolis_regular))
        )
        Text(
            text = itemInfo,
            fontFamily = FontFamily(Font(R.font.metropolis_regular))
        )
    }
}

@Preview
@Composable
fun ShowOrderDetailCard() {
    OrderDetailCard()
}