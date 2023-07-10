package com.example.tezmarket.presentation.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tezmarket.R
import com.example.tezmarket.ui.theme.Gray
import com.example.tezmarket.ui.theme.Success

@Preview(showBackground = true)
@Composable
fun OrderCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 15.dp, bottom = 5.dp)
            .shadow( 5.dp, shape = RoundedCornerShape(8.dp))
            .background(color = White, shape = RoundedCornerShape(8.dp))

    )
    {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .padding(top = 15.dp)
        )
        {
            Text(
                text = "Заказ №1947034",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                color = Black
            )
            Text(
                text = "05-03-2023",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                color = Gray
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .padding(top = 15.dp)
        )
        {
            Text(
                text = "Идентификационный номер:",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                color = Gray
            )
            Text(
                text = "IW347545345",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.metropolis_medium)),
                color = Black
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .padding(top = 5.dp)
        )
        {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Количество: ",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                    color = Gray
                )
                Text(
                    text = "3",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                    color = Black
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Общая сумма:",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_medium)),
                    color = Gray,
                    modifier = Modifier.padding(end = 5.dp)
                )
                Text(
                    text = "112$",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                    color = Black
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .padding(top = 15.dp, bottom = 15.dp)
        ) {
            OutlinedButton(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(0.8.dp, Black)
            ) {
                Text(
                    text = "Детали", fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                    color = Black
                )
            }

            Text(
                text = "Доставлено",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.metropolis_medium)),
                color = Success
            )

        }

    }
}