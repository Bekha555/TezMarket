package com.example.tezmarket.presentation.home


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCancellationBehavior
import com.airbnb.lottie.compose.LottieCompositionSpec.RawRes
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.tezmarket.R
import com.example.tezmarket.navigation.BottomNavigation
import com.example.tezmarket.presentation.HomeShimmer
import com.example.tezmarket.ui.common.SaleProduct
import com.example.tezmarket.ui.common.SaleProductScroll
import com.example.tezmarket.ui.theme.Background
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit, block = {
        homeViewModel.getHomeData()
    })

    val products = homeViewModel.productItems.collectAsLazyPagingItems().itemSnapshotList.take(6)
    val discProducts = homeViewModel.discProductItems.collectAsLazyPagingItems().itemSnapshotList.take(6)
    val recProducts = homeViewModel.recProductItems.collectAsLazyPagingItems()
    val shops = homeViewModel.shopsItems.collectAsLazyPagingItems().itemSnapshotList.take(6)
    val banners = homeViewModel.bannersUiState.data?.data ?: emptyList()
    val advertisements = homeViewModel.advertisementsUiState.data?.data ?: emptyList()

    val composition by rememberLottieComposition(
        RawRes(R.raw.loading_animation)
    )
    val error by rememberLottieComposition(RawRes(R.raw.no_internet))

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        speed = 2f,
        restartOnPlay = false,
        cancellationBehavior = LottieCancellationBehavior.Immediately
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background)
    ) {
        Scaffold(bottomBar = {
            BottomNavigation(navController = navController)
        }) { innerPadding ->
            if (homeViewModel.advertisementsUiState.isLoading || homeViewModel.advertisementsUiState.error.isNotEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    LottieAnimation(
                        composition = composition,
                        progress = progress,
                        modifier = Modifier
                            .size(150.dp)
                            .align(Alignment.Center),
                        contentScale = ContentScale.Crop
                    )
                    HomeShimmer()
                }
            } else {
                Column(modifier = Modifier.padding(innerPadding)) {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Background)
                    ) {

                        if (products.isNotEmpty()) {
                            item {
                                HomeCarousel(banners)
                            }

                            item {
                                SaleProductScroll(
                                    title = "Магазины",
                                    title_mid = "Магазины и продавцы",
                                    sale_label = "new",
                                    navController = navController,
                                    products = shops
                                )
                            }

                            item {
                                SaleProductScroll(
                                    title = "Распродажа",
                                    title_mid = "Товары со скидкой",
                                    sale_label = "new",
                                    navController = navController,
                                    products = discProducts
                                )
                            }

                            item {
                                Banner(banners)
                            }

                            item {
                                SaleProductScroll(
                                    title = "Новое",
                                    title_mid = "Только новые товары",
                                    sale_label = "new",
                                    navController = navController,
                                    products = products
                                )
                            }
                            item {
                                SaleProductScroll(
                                    title = "Объявления",
                                    title_mid = "Ваши объявления",
                                    sale_label = "new",
                                    navController = navController,
                                    products = advertisements
                                )
                            }

                            item { RecProductHome() }
                            gridItems(
                                recProducts.itemCount,
                                nColumns = 2,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                recProducts[it].let {
                                    SaleProduct(
                                        sale_label = "",
                                        width = 170.dp,
                                        onClick = { /*TODO*/ },
                                        product = it
                                    )
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun ShowHome() {
    HomeScreen(navController = rememberNavController())
}