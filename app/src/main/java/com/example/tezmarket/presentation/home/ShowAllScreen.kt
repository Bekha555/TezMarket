package com.example.tezmarket.presentation.home

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.common.Filters
import com.example.tezmarket.ui.common.SaleProduct
import com.example.tezmarket.ui.common.SaleProductList
import com.example.tezmarket.ui.theme.Background

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShowAllScreen(
    navController: NavController,
    productName: Int,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    var visible by remember {
        mutableStateOf(true)
    }

    var search = remember {
        mutableStateOf(TextFieldValue(""))
    }

    LaunchedEffect(key1 = true, block = {
        if (productName != -1 && productName != -2 && productName != -3 && productName != 0) {
            homeViewModel.getProductsByCategory(categoryId = productName)
        }
    })

    val productsByCategory = homeViewModel.productsByCategory.collectAsLazyPagingItems()
    val discProducts = homeViewModel.discProductItems.collectAsLazyPagingItems()
    val products = homeViewModel.productItems.collectAsLazyPagingItems()
    val shopsItems = homeViewModel.shopsItems.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            AppThemeTopBar(
                navController = navController,
                backBtn = true,
                icon = "search",
                shadow = false,
                onClick = { },
                title = "",
                lazyListState = LazyListState(),
                searchText = search,
                onValueChange = {}
            )
        },
        backgroundColor = Background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Filters(onClick = { }, grid = { visible = !visible }, filter = {})
            AnimatedVisibility(visible = visible) {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    when (productName) {
                        -1 -> {
                            gridItems(
                                products.itemCount,
                                nColumns = 2,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                products[it].let {
                                    SaleProduct(
                                        sale_label = "",
                                        width = 170.dp,
                                        onClick = { /*TODO*/ },
                                        product = it
                                    )
                                }
                            }
                        }

                        -2 -> {
                            gridItems(
                                discProducts.itemCount,
                                nColumns = 2,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                discProducts[it].let {
                                    SaleProduct(
                                        sale_label = "",
                                        width = 170.dp,
                                        onClick = { navController.navigate(
                                            Screen.ProductDetailsScreen.passProductDetails(
                                                it!!.id ?: 1
                                            )
                                        )},
                                        product = it
                                    )
                                }
                            }
                        }

                        -3 -> {
                            gridItems(
                                shopsItems.itemCount,
                                nColumns = 2,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                shopsItems[it].let {
                                    SaleProduct(
                                        sale_label = "",
                                        width = 170.dp,
                                        onClick = { navController.navigate(
                                            Screen.ProductDetailsScreen.passProductDetails(
                                                it!!.id ?: 1
                                            )
                                        ) },
                                        product = it
                                    )
                                }
                            }
                        }

                        else -> {
                            gridItems(
                                productsByCategory.itemCount,
                                nColumns = 2,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                productsByCategory[it].let {
                                    SaleProduct(
                                        sale_label = "",
                                        width = 170.dp,
                                        onClick = { navController.navigate(
                                            Screen.ProductDetailsScreen.passProductDetails(
                                                it!!.id ?: 1
                                            )
                                        ) },
                                        product = it
                                    )
                                }
                            }
                        }
                    }
                }
            }
            AnimatedVisibility(visible = !visible) {
                LazyColumn(content = {
                    when (productName) {
                        -1 -> {
                            items(products) {
                                SaleProductList(navController, product = it)
                            }
                        }

                        -2 -> {
                            items(discProducts) {
                                SaleProductList(navController, product = it)
                            }
                        }

                        else -> {
                            items(productsByCategory) {
                                SaleProductList(navController, product = it)
                            }
                        }
                    }

                })
            }
        }
    }
}



