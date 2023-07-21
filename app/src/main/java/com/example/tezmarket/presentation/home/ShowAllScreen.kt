package com.example.tezmarket.presentation.home

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.tezmarket.R
import com.example.tezmarket.data.remote.model.categories.Categories
import com.example.tezmarket.data.remote.model.products.Data
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.presentation.categories.CategoriesScreenOld
import com.example.tezmarket.presentation.categories.CategoriesViewModel
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
    homeViewModel: HomeViewModel = hiltViewModel(),
    categoriesViewModel: CategoriesViewModel = hiltViewModel()
) {

    var visible by remember {
        mutableStateOf(true)
    }

    var search = remember {
        mutableStateOf(TextFieldValue(""))
    }

    val composition by rememberLottieComposition(

        LottieCompositionSpec
            // here `code` is the file name of lottie file
            // use it accordingly
            .RawRes(R.raw.purple_loading)
    )
    var isPlaying by remember {
        mutableStateOf(true)
    }

// for speed
    var speed by remember {
        mutableStateOf(1f)
    }

    // to control the animation
    val progress by animateLottieCompositionAsState(
        // pass the composition created above
        composition,

        // Iterates Forever
        iterations = LottieConstants.IterateForever,

        // pass isPlaying we created above,
        // changing isPlaying will recompose
        // Lottie and pause/play
        isPlaying = isPlaying,

        // pass speed we created above,
        // changing speed will increase Lottie
        speed = speed,

        // this makes animation to restart
        // when paused and play
        // pass false to continue the animation
        // at which it was paused
        restartOnPlay = false

    )
    val context = LocalContext.current

    LaunchedEffect(key1 = true, block = {
        if (productName != -1 && productName != -2 && productName != -3 && productName != 0) {
            homeViewModel.getProductsByCategory(categoryId = productName)
        }
    })

    val categoriesList = categoriesViewModel.categoriesUiState.data?.data ?: emptyList()
    var categoryName: String = ""
    Log.d("Categories Josef", categoriesList.toString())

    if (categoriesList.isNotEmpty() && productName>0) {
        Toast.makeText(context, "product Name ${productName - 1}, category name ${categoriesList.get(productName - 1).name}", Toast.LENGTH_SHORT).show()
        categoryName = categoriesList[productName - 1].name.toString()
    }

    val productsByCategory = homeViewModel.productsByCategory.collectAsLazyPagingItems()
    val discProducts = homeViewModel.discProductItems.collectAsLazyPagingItems()
    val products = homeViewModel.productItems.collectAsLazyPagingItems()
    val shopsItems = homeViewModel.shopsItems.collectAsLazyPagingItems()
    val filteredProducts = homeViewModel.filteredProductsUiState.data?.data ?: emptyList()

    val filteredData = hashMapOf<String, Any>()

    Scaffold(
        topBar = {
            AppThemeTopBar(
                navController = navController,
                backBtn = true,
                icon = "search",
                shadow = false,
                onClick = { },
                title = when (productName) {
                    -1 -> {
                        "Новое"
                    }
                    -2 -> {
                        "Распродажа"
                    }
                    -3 -> {
                        "Магазины"
                    }
                    else -> {
                        categoryName
                    }
                },
                lazyListState = LazyListState(),
                searchText = search,
                onValueChange = {
                    search.value = it
                    filteredData["title"] = it.text
                    homeViewModel.getFilteredProducts(filteredData)
                }
            )
        },
        backgroundColor = Background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Filters(onClick = { },
                grid = {
                    visible = !visible
                    Toast.makeText(context, "grid icon is pressed", Toast.LENGTH_SHORT).show()
                },
                filter = {})
            AnimatedVisibility(visible = visible) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    if (search.value.text.isNotEmpty()) {
                        Toast.makeText(context, filteredProducts.toString(), Toast.LENGTH_SHORT)
                            .show()
                        if (homeViewModel.filteredProductsUiState.isLoading) {
                            item {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    LottieAnimation(
                                        composition,
                                        progress,
                                        modifier = Modifier
                                            .size(100.dp)
                                            .align(Alignment.Center)
                                    )
                                }

                            }
                        } else {
                            gridItems(
                                filteredProducts.size,
                                nColumns = 2,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                filteredProducts[it].let {
                                    SaleProduct(
                                        sale_label = "",
                                        width = 170.dp,
                                        onClick = { /*TODO*/ },
                                        product = it
                                    )
                                }
                            }
                        }
                    } else {

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
                                            onClick = {
                                                navController.navigate(
                                                    Screen.ProductDetailsScreen.passProductDetails(
                                                        it!!.id ?: 1
                                                    )
                                                )
                                            },
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
                                            onClick = {
                                                navController.navigate(
                                                    Screen.ProductDetailsScreen.passProductDetails(
                                                        it!!.id ?: 1
                                                    )
                                                )
                                            },
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
                                            onClick = {
                                                navController.navigate(
                                                    Screen.ProductDetailsScreen.passProductDetails(
                                                        it!!.id ?: 1
                                                    )
                                                )
                                            },
                                            product = it
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            AnimatedVisibility(visible = !visible) {
                LazyColumn(content = {
                    if (search.value.text.isNotEmpty()) {
                        items(filteredProducts) {
                            SaleProductList(navController, product = it)
                        }
                    } else {
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

                    }
                })
            }
        }
    }

}



