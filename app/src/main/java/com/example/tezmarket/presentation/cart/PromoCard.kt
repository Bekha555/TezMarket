package com.example.tezmarket.presentation.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tezmarket.R
import com.example.tezmarket.ui.theme.Error
import com.example.tezmarket.ui.theme.Gray
import com.example.tezmarket.ui.theme.Primary

@Preview
@Composable
fun PromoCard() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(bottom = 20.dp)
            .fillMaxWidth()
            .background(color = White, shape = RoundedCornerShape(10.dp))
            .clip(shape = RoundedCornerShape(10.dp))
    ) {

        Box(modifier = Modifier
            .background(color = Error)
            .size(80.dp)) {
            Image(
                painter = painterResource(id = R.drawable.promocode_icon),
                contentDescription = "", modifier = Modifier
                    .size(65.dp)
                    .align(Alignment.Center)
            )
        }

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = 15.dp)
                .width(135.dp)

        ) {
            Text(
                text = "Персональное предложение",
                fontSize = 14.sp, maxLines = 2
            )
            Text(
                text = "mypromocode2022",
                fontSize = 11.sp,
                modifier = Modifier.padding(top = 5.dp)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(start = 15.dp, end = 15.dp)
        ) {
            Text(
                text = "осталось 6 дней",
                fontSize = 11.sp,
                maxLines = 1,
                color = Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Primary,
                    contentColor = White
                ),
                elevation = ButtonDefaults.elevation(defaultElevation = 4.dp),
                modifier = Modifier
                    .requiredHeight(36.dp)
                    .requiredWidth(93.dp)
            ) {
                Text(text = "Принять", fontSize = 12.sp)
            }
        }


    }
}