@file:Suppress("UNUSED_EXPRESSION")

package com.example.tezmarket.ui.common

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tezmarket.R
import com.example.tezmarket.data.remote.model.products.Data
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.ui.theme.Background
import com.example.tezmarket.ui.theme.Gray

@Composable
fun <T> SaleProductScroll(
    title: String,
    title_mid: String,
    sale_label: String,
    navController: NavController,
    products: List<T>
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .background(color = Transparent)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 10.dp)
                .background(color = Background)
        )
        {
            Text(
                text = title,
                fontSize = 34.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.metropolis_bold))
            )
            TextButton(onClick = {
                when (products.first()) {
                    is Data -> {
                        navController.navigate(Screen.ShowAllScreen.passProductName(-1))
                    }

                    is com.example.tezmarket.data.remote.model.discProducts.Data -> {
                        navController.navigate(Screen.ShowAllScreen.passProductName(-2))
                    }

                    is com.example.tezmarket.data.remote.model.shops.Data -> {
                        navController.navigate(Screen.ShowAllScreen.passProductName(-3))
                    }

                    is com.example.tezmarket.data.remote.model.advertisements.Data -> {
                        navController.navigate(Screen.ShowAllScreen.passProductName(-4))
                    }
                }
            }) {
                Text(text = "Показать все", fontSize = 11.sp, color = Black)
            }
        }
        Text(
            text = title_mid,
            fontSize = 11.sp,
            color = Gray,
            fontFamily = FontFamily(
                Font(R.font.metropolis_regular)
            ), modifier = Modifier.padding(bottom = 20.dp, start = 20.dp)
        )

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState()),
            // state = rememberLazyListState()
        ) {
//            val productList = products.subList(0, 6)
            for (item in products) {
                Log.d("debug", item.toString())
                Box(modifier = Modifier.padding(start = 20.dp)) {
                    SaleProduct(
                        sale_label = sale_label,
                        width = 150.dp,
                        onClick = {

                            when (item) {
                                is com.example.tezmarket.data.remote.model.advertisements.Data -> {
                                    navController.navigate(
                                        Screen.AdvertisementDetailsScreen.passAdvertisementDetails(
                                            item.id ?: 1
                                        )
                                    )
                                }


                                else -> {
                                    navController.navigate(
                                        Screen.ProductDetailsScreen.passProductDetails(
                                            when (item) {
                                                is Data -> {
                                                    item.id ?: 1
                                                }

                                                is com.example.tezmarket.data.remote.model.recproducts.Data -> {
                                                    item.id ?: 1
                                                }

                                                is com.example.tezmarket.data.remote.model.discProducts.Data -> {
                                                    item.id ?: 1
                                                }

                                                is com.example.tezmarket.data.remote.model.advertisements.Data -> {
                                                    item.id ?: 1
                                                }

                                                else -> {
                                                    0
                                                }
                                            }
                                        )
                                    )
                                }
                            }


                        },
                        product = item
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun showSales() {
    SaleProductScroll(
        title = "Распродажа",
        title_mid = "Супер летняя распродажа",
        sale_label = "20",
        navController = rememberNavController(),
        products = emptyList<String>()
    )
}