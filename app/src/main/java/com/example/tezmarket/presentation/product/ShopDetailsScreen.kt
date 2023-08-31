package com.example.tezmarket.presentation.product

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tezmarket.R
import com.example.tezmarket.presentation.ProductShimmer
import com.example.tezmarket.presentation.favorites.FavoritesViewModel
import com.example.tezmarket.presentation.home.HomeViewModel
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.theme.Background
import com.example.tezmarket.ui.theme.Gray
import com.example.tezmarket.ui.theme.LightPurple
import com.example.tezmarket.ui.theme.Primary
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.roundToInt

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ShopDetailScreen(
    navController: NavController,
    shopId: Int,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
//    LaunchedEffect(key1 = Unit, block = {
//        Log.d("debug", shopId.toString())
//        homeViewModel.getShopById(shop_id = shopId)
//    })
//
//    var searchText = remember {
//        mutableStateOf(TextFieldValue(""))
//    }
//
//    val shopByIdData = homeViewModel.shopByIdUiState.data?.data
//    // val similarProducts = homeViewModel.simularProductUiState.data?.data ?: emptyList()
//    val images = shopByIdData?.logo
//    Log.d("debug", shopByIdData.toString())
//
//
//    var visible by remember {
//        mutableStateOf(true)
//    }
//
//    Scaffold(topBar = {
//        if (visible) {
//            AppThemeTopBar(
//                navController = navController,
//                title = shopByIdData?.name ?: "",
//                onClick = {},
//                shadow = false,
//                searchText = searchText,
//                onValueChange = {},
//                icon = "",
//                backBtn = true,
//                modifier = Modifier,
//                lazyListState = LazyListState()
//            )
//        }
//    }) {
//
//        val pagerState = rememberPagerState()
//
//        if (homeViewModel.shopByIdUiState.isLoading || homeViewModel.shopByIdUiState.error.isNotEmpty()) {
//            ProductShimmer()
//        } else {
//
//            AnimatedVisibility(
//                visible = visible,
//                enter = fadeIn(
//                    animationSpec = TweenSpec(
//                        durationMillis = 300,
//                        easing = FastOutSlowInEasing
//                    )
//                ),
//                exit = fadeOut(
//                    animationSpec = tween(
//                        durationMillis = 300,
//                        easing = FastOutSlowInEasing
//                    )
//                )
//            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Background)
//                        .padding(it)
//                        .verticalScroll(rememberScrollState())
//                ) {
//                    HorizontalPager(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(420.dp),
//                        count = images.size,
//                        state = pagerState
//                    ) { page ->
//                        val imageUrl = images[page]
//                        AsyncImage(
//                            model = imageUrl,
//                            contentScale = ContentScale.Crop,
//                            contentDescription = null,
//                            onLoading = {},
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .clickable(onClick = {
//                                    selectedImageIndex = page
//                                    visible = false
//                                })
//                        )
//                    }
//                    if (images.size > 1) {
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(top = 8.dp),
//                            horizontalArrangement = Arrangement.Center
//                        ) {
//                            for (i in 0 until images.size) {
//                                val color =
//                                    if (pagerState.currentPage == i) Primary else LightPurple
//                                Box(
//                                    modifier = Modifier
//                                        .padding(horizontal = 4.dp)
//                                        .size(8.dp)
//                                        .background(color = color, shape = CircleShape)
//                                )
//                            }
//                        }
//                    }
//
//                    Column(
//                        modifier = Modifier.padding(vertical = 10.dp)
//                    ) {
//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(bottom = 10.dp)
//                                .padding(horizontal = 10.dp)
//                        ) {
//                            Text(
//                                modifier = Modifier.align(alignment = Alignment.TopStart),
//                                text = "${shopByIdData?.createdAt}",
//                                fontFamily = FontFamily(Font(R.font.metropolis_regular)),
//                                fontSize = 12.sp,
//                                color = Gray
//                            )
//                            IconButton(
//                                onClick = {
//                                    if (selected == 0) {
//                                        selected = 1
//                                    } else {
//                                        selected = 0
//                                    }
//                                    favoritesViewModel.favoritesToggle(
//                                        productId = advertisementId,
//                                        productType = "product"
//                                    )
//                                }, modifier = Modifier
//                                    .size(40.dp)
//                                    .shadow(elevation = 2.dp, clip = true, shape = CircleShape)
//                                    .clip(CircleShape)
//                                    .background(color = Color.White)
//                                    .align(Alignment.CenterEnd)
//                            ) {
//                                Image(
//                                    painter = painterResource(
//                                        id = if (selected == 0 && productByIdData?.isFavorite == false) {
//                                            R.drawable.heart_icon
//                                        } else {
//                                            R.drawable.filled_heart_icon
//                                        }
//                                    ),
//                                    contentDescription = "heart",
//                                    modifier = Modifier
//                                        .size(15.dp)
//                                )
//                            }
//                        }
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(horizontal = 10.dp),
//                            horizontalArrangement = Arrangement.SpaceBetween,
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Text(
//                                text = "${shopByIdData?.name}",
//                                fontFamily = FontFamily(Font(R.font.metropolis_bold)),
//                                fontSize = 24.sp
//                            )
//                            Text(
//                                text = "${shopByIdData?.price} TJS",
//                                fontFamily = FontFamily(Font(R.font.metropolis_bold)),
//                                fontSize = 24.sp
//                            )
//                        }
//                        Text(
//                            text = "${shopByIdData?.category?.title}",
//                            fontFamily = FontFamily(Font(R.font.metropolis_regular)),
//                            fontSize = 12.sp,
//                            color = Gray,
//                            modifier = Modifier.padding(horizontal = 10.dp)
//                        )
//
//                        Text(
//                            modifier = Modifier.padding(vertical = 25.dp, horizontal = 10.dp),
//                            text = "${shopByIdData?.desc}",
//                            fontFamily = FontFamily(Font(R.font.metropolis_regular))
//                        )
//
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(top = 10.dp)
//                                .horizontalScroll(
//                                    rememberScrollState()
//                                ),
//                            horizontalArrangement = Arrangement.spacedBy(10.dp)
//                        ) {
//                        }
//                    }
//
//                    if (homeViewModel.advertisementByIduiState.data?.data?.attributes?.isNotEmpty() == true) {
//                        Text(
//                            text = "Характеристики",
//                            fontFamily = FontFamily(Font(R.font.metropolis_bold)),
//                            fontSize = 24.sp,
//                            modifier = Modifier.padding(start = 10.dp)
//                        )
//                        for (item in homeViewModel.advertisementByIduiState.data?.data?.attributes!!) {
//                            Column(
//                                modifier = Modifier
//                                    .padding(top = 10.dp)
//                                    .padding(horizontal = 10.dp)
//                            ) {
//                                Text(text = item.name.toString(), fontSize = 14.sp, color = Gray)
//                                Text(text = item.value.toString())
//                            }
//                        }
//                        CallSection(name = shopId?.client?.name.toString(), number = shopId?.client?.phone!!)
//
//
//                    }
//
//                }
//            }
//        }
//    }
}