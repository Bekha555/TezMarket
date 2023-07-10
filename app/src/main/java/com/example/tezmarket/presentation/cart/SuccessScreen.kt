package com.example.tezmarket.presentation.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tezmarket.R
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.ui.common.AppThemeButton

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SuccessScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            Column {
                AppThemeButton(text = "ПРОДОЛЖИТЬ ПОКУПКИ") {
                    navController.navigate(Screen.HomeScreen.route)
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        },
        backgroundColor = White
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.confirm_success_icon),
                contentDescription = "success",
                modifier = Modifier.padding(top = 170.dp, bottom = 50.dp)
            )
            Text(text = "Успешно!", color = Black, fontSize = 34.sp, fontWeight = FontWeight.Bold)
            Text(
                text = "Ваш заказ будет доставлен в ближайшее время.\n Спасибо, что выбрали наше приложение!",
                fontSize = 14.sp,
                color = Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 12.dp)
            )
        }

    }
}