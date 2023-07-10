package com.example.tezmarket.navigation

sealed class Screen(val route: String) {
    object RegistrationScreen : Screen("registration_screen")
    object AuthorizationScreen : Screen("authorization_screen")
    object ForgetPasswordScreen : Screen("forget_password_screen")
    object BrandFilterScreen : Screen("brand_filter_screen")
    object HomeScreen : Screen("home_screen")
    object CategoriesScreen : Screen("categories_screen")
    object CatalogScreen : Screen("catalog_screen")
    object CartScreen : Screen("cart_screen")
    object FavoritesScreen : Screen("favorites_screen")
    object ProfileScreen : Screen("profile_screen")
    object CartCheckout : Screen("cart_checkout_screen")
    object RatingScreen : Screen("rating_screen?product_id={product_id}") {
        fun passProductDetails(productId: Int): String {
            return "rating_screen?product_id=$productId"
        }
    }

    object SuccessScreen : Screen("success_screen")
    object ProductDetailsScreen : Screen("product_details_screen?product_id={product_id}") {
        fun passProductDetails(productId: Int): String {
            return "product_details_screen?product_id=$productId"
        }
    }

    object OrderDetailsScreen : Screen("order_details_screen")
    object MyOrdersScreen : Screen("my_orders_screen")
    object ShowAllScreen : Screen("show_all_screen?productName={productName}") {
        fun passProductName(productName: Int): String {
            return "show_all_screen?productName=$productName"
        }
    }

    object AdressesScreen : Screen("adresses_screen")

    object AddressAddScreen : Screen("add_adresses_screen?adress_id={adress_id}") {

        fun passAdressId(adressId: Int): String {
            return "add_adresses_screen?adress_id=$adressId"

        }
    }


}