package com.example.tezmarket.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tezmarket.navigation.BottomNavigation
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.common.AppThemeTopText
import com.example.tezmarket.ui.theme.Background

@Composable
fun ProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }

    Scaffold(
        topBar = {
            AppThemeTopBar(
                title = "",
                backBtn = false,
                icon = "search",
                searchText = searchText,
                onValueChange = {},
                onClick = { /*TODO*/ },
                navController = navController,
                shadow = false,
                modifier = Modifier,
                lazyListState = LazyListState()
            )
        },
        bottomBar = { BottomNavigation(navController = navController) },
        backgroundColor = Background
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AppThemeTopText(
                text = "Мой профиль",
                color = Transparent,
                shadow = false,
                modifier = Modifier
            )
            ProfileSection()
            ProfileItem(
                title = "Мои заказы",
                subtitle = "Уже есть 12 заказов",
                onClick = { navController.navigate(Screen.MyOrdersScreen.route) })
            ProfileItem(
                title = "Мои объявления",
                subtitle = "4 объявления",
                onClick = { navController.navigate(Screen.MyAdvertisementsScreen.route) })
            ProfileItem(title = "Адреса доставки", subtitle = "3 адреса", onClick = {})
            ProfileItem(title = "Способы оплаты", subtitle = "Visa  **34", onClick = {})
            ProfileItem(
                title = "Промокоды",
                subtitle = "У вас есть специальные промокоды",
                onClick = {})
            ProfileItem(title = "Мои отзывы", subtitle = "Отзывы к 4 товарам", onClick = {})
            ProfileItem(title = "Настройки", subtitle = "Уведомления, пароль", onClick = {})
        }
    }
}