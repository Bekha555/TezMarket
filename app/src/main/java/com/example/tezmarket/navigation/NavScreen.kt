package com.example.tezmarket.navigation

import androidx.annotation.DrawableRes
import com.example.tezmarket.R

sealed class NavScreen(
    val route: String, val title: String, @DrawableRes val icon: Int
) {

    object HomeScreen : NavScreen(
        route = "home_screen",
        title = "Главная",
        icon = R.drawable.home_img
    )

    object CategoriesScreen : NavScreen(
        route = "categories_screen",
        title = "Категории",
        icon = R.drawable.category_img
    )

    object CartScreen : NavScreen(
        route = "cart_screen",
        title = "Корзина",
        icon = R.drawable.bin_img
    )

    object FavoriteScreen : NavScreen(
        route = "favorites_screen",
        title = "Избранное",
        icon = R.drawable.heart_img
    )

    object ProfileScreen : NavScreen(
        route = "profile_screen",
        title = "Профиль",
        icon = R.drawable.profile_img
    )

}