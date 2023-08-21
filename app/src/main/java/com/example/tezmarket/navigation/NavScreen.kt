package com.example.tezmarket.navigation

import androidx.annotation.DrawableRes
import com.example.tezmarket.R

sealed class NavScreen(
    val route: String, val title: String, @DrawableRes val icon: Int, @DrawableRes val selected_icon: Int
) {

    object HomeScreen : NavScreen(
        route = "home_screen",
        title = "Главная",
        icon = R.drawable.home_icon,
        selected_icon = R.drawable.home_filled
    )

    object CategoriesScreen : NavScreen(
        route = "categories_screen",
        title = "Категории",
        icon = R.drawable.categories_icon,
        selected_icon = R.drawable.categories_filled
    )

    object CartScreen : NavScreen(
        route = "cart_screen",
        title = "Корзина",
        icon = R.drawable.cart_icon,
        selected_icon = R.drawable.cart_filled
    )

    object FavoriteScreen : NavScreen(
        route = "favorites_screen",
        title = "Избранное",
        icon = R.drawable.favorites_icon,
        selected_icon = R.drawable.favorites_filled
    )

    object ProfileScreen : NavScreen(
        route = "profile_screen",
        title = "Профиль",
        icon = R.drawable.profile_icon,
        selected_icon = R.drawable.profile_filled
    )

}