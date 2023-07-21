package com.example.tezmarket.presentation.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tezmarket.R
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.ui.common.AppThemeButton
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.theme.Background
import com.example.tezmarket.ui.theme.Primary

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CartCheckout(navController: NavController) {
    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }

    Scaffold(
        topBar = {
            AppThemeTopBar(
                title = "Проверка",
                backBtn = true,
                icon = "",
                shadow = true,
                searchText = searchText,
                onValueChange = {},
                onClick = { /*TODO*/ },
                navController = navController,
                modifier = Modifier,
                lazyListState = LazyListState()
            )
        },
        bottomBar = {
            Column {
                AppThemeButton(text = "ПОДТВЕРДИТЬ ЗАКАЗ") {
                    navController.navigate(Screen.SuccessScreen.route)
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        },
        backgroundColor = Background
    ) {
        Column() {
            Box(
                Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 10.dp)
            ) {
                Text(
                    text = "Адрес доставки",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp)
                    .shadow(
                        10.dp,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .background(color = White, shape = RoundedCornerShape(15.dp))
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                ) {
                    Text(text = "Сумая")

                    Text(
                        text = "Редактировать",
                        color = Primary,
                        modifier = Modifier.clickable(onClick = {})
                    )
                }
                Text(
                    text = "пр. Рудаки, 100, 52 \n" +
                            "Душанбе, 734000, Таджикистан",
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 20.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 50.dp)
            ) {
                Text(
                    text = "Способ доставки", fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {

                Box(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .requiredWidth(100.dp)
                        .shadow(
                            10.dp,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .clip(shape = RoundedCornerShape(15.dp))
                        .background(color = White)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.fedex_icon),
                        contentDescription = "", modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.TopCenter)
                            .padding(horizontal = 5.dp)
                            .padding(bottom = 15.dp)
                    )
                    Text(
                        text = " 1-2 дня",
                        color = Gray,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(top = 40.dp)
                            .padding(horizontal = 20.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .requiredWidth(100.dp)
                        .shadow(
                            10.dp,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .clip(shape = RoundedCornerShape(15.dp))
                        .background(color = White)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.usps_icon),
                        contentDescription = "", modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.TopCenter)
                            .padding(horizontal = 5.dp)
                            .padding(bottom = 15.dp)
                    )
                    Text(
                        text = " 1-3 дня",
                        color = Gray,
                        maxLines = 1,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(top = 40.dp)
                            .padding(horizontal = 10.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .requiredWidth(100.dp)
                        .shadow(
                            10.dp,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .clip(shape = RoundedCornerShape(15.dp))
                        .background(color = White)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dhl_icon),
                        contentDescription = "", modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.TopCenter)
                            .padding(horizontal = 5.dp)
                            .padding(bottom = 15.dp)
                    )
                    Text(
                        text = " 1-2 дня",
                        color = Gray,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(top = 40.dp)
                            .padding(horizontal = 20.dp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(140.dp)
                    .padding(top = 50.dp)
                    .padding(horizontal = 20.dp)
            )
            {
// Blok 1
                Text(
                    text = "Заказ:",
                    fontSize = 14.sp,
                    color = Gray,
                    modifier = Modifier.align(Alignment.TopStart)
                )
                Text(
                    text = "120TJS",
                    fontSize = 18.sp,
                    color = Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.TopEnd)
                )
// Blok 2
                Text(
                    text = "Доставка:",
                    fontSize = 14.sp,
                    color = Gray,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                Text(
                    text = "160TJS",
                    fontSize = 18.sp,
                    color = Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
//Blok 3
                Text(
                    text = "Общая сумма:",
                    fontSize = 14.sp,
                    color = Gray,
                    modifier = Modifier.align(Alignment.BottomStart)
                )
                Text(
                    text = "143TJS",
                    fontSize = 18.sp,
                    color = Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.BottomEnd)
                )


            }


        }
    }
}