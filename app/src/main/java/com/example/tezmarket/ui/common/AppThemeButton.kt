@file:Suppress("UNUSED_EXPRESSION")

package com.example.tezmarket.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tezmarket.ui.theme.Primary

@Composable
fun AppThemeButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Primary),
        elevation = ButtonDefaults.elevation(defaultElevation = 5.dp),
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(text = text, fontSize = 14.sp, color = Color.White)
    }
}


@Composable
fun BottomAppBarButton(
    confirmText: String,
    cancelText: String,
    cancel: () -> Unit,
    confirm: () -> Unit,
    bottomElevation: Dp
) {
    BottomAppBar(
        backgroundColor = Transparent,
        elevation = bottomElevation,
        modifier = Modifier.height(70.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = cancel,
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = White),
                elevation = ButtonDefaults.elevation(defaultElevation = 5.dp),
                border = BorderStroke(width = 1.dp, color = Black),
                modifier = Modifier
                    .width(160.dp)
                    .height(40.dp)
            ) {
                Text(text = cancelText, fontSize = 14.sp, color = Black)
            }
            Button(
                onClick = confirm,
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Primary),
                elevation = ButtonDefaults.elevation(defaultElevation = 5.dp),
                modifier = Modifier
                    .width(160.dp)
                    .height(40.dp)
            ) {
                Text(text = confirmText, fontSize = 14.sp, color = White)
            }
        }
    }
}


@Preview(showBackground = true, name = "AppThemeButtonPreview")
@Composable
fun DefaultPreview() {
    BottomAppBarButton(
        cancelText = "Отказаться",
        confirmText = "Применить",
        cancel = {},
        confirm = {},
        bottomElevation = 4.dp
    )
}

