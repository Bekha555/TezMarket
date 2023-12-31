package com.example.tezmarket.navigation

import AdvertisementDetailsScreen
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tezmarket.presentation.AuthorizationScreen
import com.example.tezmarket.presentation.ForgetPasswordScreen
import com.example.tezmarket.presentation.RegistrationScreen
import com.example.tezmarket.presentation.auth.CartScreen
import com.example.tezmarket.presentation.auth.CategoriesScreen
import com.example.tezmarket.presentation.cart.CartCheckout
import com.example.tezmarket.presentation.cart.SuccessScreen
import com.example.tezmarket.presentation.catalog.CatalogScreen
import com.example.tezmarket.presentation.favorites.FavoriteScreen
import com.example.tezmarket.presentation.home.HomeScreen
import com.example.tezmarket.presentation.home.ShowAllScreen
import com.example.tezmarket.presentation.product.ProductDetailsScreen
import com.example.tezmarket.presentation.product.ShopDetailScreen
import com.example.tezmarket.presentation.profile.AddAdvertisementScreen
import com.example.tezmarket.presentation.profile.AddressAddScreen
import com.example.tezmarket.presentation.profile.Addresses
import com.example.tezmarket.presentation.profile.ChooseAttributs
import com.example.tezmarket.presentation.profile.ChooseCategory
import com.example.tezmarket.presentation.profile.MyAdvertisementsScreen
import com.example.tezmarket.presentation.profile.MyOrderScreen
import com.example.tezmarket.presentation.profile.OrderDetailsScreen
import com.example.tezmarket.presentation.profile.ProfileScreen
import com.example.tezmarket.presentation.rating.RatingScreen


@Composable
fun AppNavigation(navController: NavHostController) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RegistrationScreen.route) {

        composable(route = Screen.RegistrationScreen.route) {
            RegistrationScreen(navController)
        }
        composable(route = Screen.AuthorizationScreen.route) {
            AuthorizationScreen(navController)
        }
        composable(route = Screen.ForgetPasswordScreen.route) {
            ForgetPasswordScreen(navController)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.CategoriesScreen.route) {
            CategoriesScreen(navController)
        }
        composable(route = Screen.CatalogScreen.route) {
            CatalogScreen(navController)
        }
        composable(route = Screen.CartScreen.route) {
            CartScreen(navController, lazyListState = LazyListState())
        }
        composable(route = Screen.FavoritesScreen.route) {
            FavoriteScreen(navController, lazyListState = LazyListState())
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navController)
        }
        composable(route = Screen.CartCheckout.route) {
            CartCheckout(navController)
        }
        composable(route = Screen.SuccessScreen.route) {
            SuccessScreen(navController)
        }
        composable(
            route = Screen.ProductDetailsScreen.route,
            arguments = listOf(navArgument("product_id") {
                type = NavType.IntType
            })
        ) {
            ProductDetailsScreen(navController, it.arguments!!.getInt("product_id"))
        }
        composable(
            route = Screen.AdvertisementDetailsScreen.route,
            arguments = listOf(navArgument("advertisement_id") {
                type = NavType.IntType
            })
        ) {
            AdvertisementDetailsScreen(navController, it.arguments!!.getInt("advertisement_id"))
        }

        composable(
            route = Screen.ShopDetailsScreen.route,
            arguments = listOf(navArgument("shop_id") {
                type = NavType.IntType
            })
        ) {
            ShopDetailScreen(navController, it.arguments!!.getInt("shop_id"))
        }


        composable(route = Screen.OrderDetailsScreen.route) {
            OrderDetailsScreen(navController)
        }
        composable(route = Screen.MyOrdersScreen.route) {
            MyOrderScreen(navController)
        }
        composable(
            route = Screen.RatingScreen.route,
            arguments = listOf(navArgument("product_id") {
                type = NavType.IntType
            })
        ) {
            RatingScreen(
                navController,
                it.arguments!!.getInt(("product_id")),
                lazyListState = LazyListState()
            )
        }
        composable(
            route = Screen.ShowAllScreen.route,
            arguments = listOf(navArgument(name = "productName") {
                type = NavType.IntType
            })
        ) {
            ShowAllScreen(navController, productName = it.arguments!!.getInt("productName"))
        }
        composable(route = Screen.AdressesScreen.route) {
            Addresses(navController)
        }
        composable(
            route = Screen.AddressAddScreen.route,
            arguments = listOf(navArgument(name = "adress_id") { type = NavType.IntType })
        ) {
            AddressAddScreen(navController, adressId = it.arguments!!.getInt("adress_id"))
        }
        composable(route = Screen.MyAdvertisementsScreen.route) {
            MyAdvertisementsScreen(navController)
        }
        composable(route = Screen.AddAdvertisementScreen.route) {
            AddAdvertisementScreen(navController)
        }
        composable(route = Screen.ChooseCategory.route) {
            ChooseCategory(navController)
        }
        composable(
            route = Screen.ChooseAttributs.route,
            arguments = listOf(navArgument(name = "category_id") { type = NavType.IntType })
        ) {
            ChooseAttributs(categoryId = it.arguments!!.getInt("category_id"), navController)
        }

    }


}
