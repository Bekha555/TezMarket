package com.example.tezmarket.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tezmarket.R
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.common.BottomAppBarButton
import com.example.tezmarket.ui.theme.Gray
import com.example.tezmarket.ui.theme.Success

@Composable
fun OrderDetailsScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }

    Scaffold(topBar = {
        AppThemeTopBar(
            title = "Детали заказа",
            backBtn = true,
            icon = "search",
            searchText = searchText,
            onValueChange = {},
            onClick = { /*TODO*/ },
            navController = navController,
            shadow = true,
            modifier = Modifier,
            lazyListState = LazyListState()
        )
    }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            item {
                Column(
                    modifier = Modifier.padding(top = 30.dp, bottom = 15.dp, start = 20.dp, end = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Заказ №1947034",
                            fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                            fontSize = 16.sp
                        )
                        Text(
                            text = "05-12-2019",
                            color = Gray,
                            fontFamily = FontFamily(Font(R.font.metropolis_regular))
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "ИД номер:",
                            color = Gray,
                            fontFamily = FontFamily(Font(R.font.metropolis_regular))
                        )
                        Text(
                            text = "IW3475453455",
                            fontFamily = FontFamily(Font(R.font.metropolis_bold))
                        )
                        Text(
                            text = "Delivered",
                            color = Success,
                            fontFamily = FontFamily(Font(R.font.metropolis_regular))
                        )
                    }
                    Text(text = "3 items", fontFamily = FontFamily(Font(R.font.metropolis_regular)))
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(420.dp).padding(horizontal = 20.dp)
                        .verticalScroll(scrollState)
                ) {
                    for (i in 1..7) {
                        OrderDetailCard()
                    }
                }
            }
            item {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(
                        text = "Информация о заказе",
                        fontFamily = FontFamily(Font(R.font.metropolis_regular))
                    )
                    OrderDetailsItems(
                        itemName = "Адрес доставки",
                        itemInfo = "пр. Рудаки, 100, 52, Душанбе,\n" +
                                "734000, Таджикистан"
                    )
                    OrderDetailsItems(itemName = "Способ оплаты", itemInfo = "**** **** **** 3947")
                    OrderDetailsItems(
                        itemName = "Способ доставки",
                        itemInfo = "FedEx, 3 days, 10TJS"
                    )
                    OrderDetailsItems(itemName = "Скидка", itemInfo = "10%, Персональный промокод")
                    OrderDetailsItems(itemName = "Общая сумма", itemInfo = "943TJS")
                }
            }
            item {
                BottomAppBarButton(
                    confirmText = "Оставить отзыв",
                    cancelText = "Заказать снова",
                    cancel = { /*TODO*/ },
                    confirm = { /*TODO*/ },
                    bottomElevation = 50.dp
                )
            }
        }
    }
}



