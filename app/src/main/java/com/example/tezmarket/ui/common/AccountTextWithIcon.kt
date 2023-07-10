package com.example.tezmarket.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tezmarket.R


@Composable
fun AccountTextWithIcon(text: String) {
    Box {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 80.dp)
        ) {
            Text(
                text = text,
                maxLines = 2,
                fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(100.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.tcell_icon),
                    contentDescription = "tcell_icon",

                )
            }
        }
    }
}

@Preview
@Composable
fun ShowAccountTextWithIcon() {
    AccountTextWithIcon(text = "Или зарегистрируйтесь с помощью учетной записи MyTcell")
}