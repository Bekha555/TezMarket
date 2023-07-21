package com.example.tezmarket.presentation.product

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tezmarket.R
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.presentation.ProductShimmer
import com.example.tezmarket.presentation.cart.CartViewModel
import com.example.tezmarket.presentation.favorites.FavoritesViewModel
import com.example.tezmarket.presentation.home.HomeViewModel
import com.example.tezmarket.presentation.rating.RatingBar
import com.example.tezmarket.ui.common.AppThemeButton
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.common.SaleProduct
import com.example.tezmarket.ui.theme.Background
import com.example.tezmarket.ui.theme.Gray
import com.example.tezmarket.ui.theme.LightGray

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProductDetailsScreen(
    navController: NavController,
    productId: Int,
    homeViewModel: HomeViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit, block = {
        Log.d("debug", productId.toString())
        homeViewModel.getProductById(productId = productId)
        homeViewModel.getSimularProducts(productId = productId)
    })

    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val productByIdData = homeViewModel.productByIdUiState.data?.data
    val similarProducts = homeViewModel.simularProductUiState.data?.data ?: emptyList()


    Log.d("debug", productByIdData.toString())

    var selected by remember {
        mutableStateOf(if (productByIdData?.isFavorite == true) 1 else 0)
    }

    Scaffold(topBar = {
        AppThemeTopBar(
            navController = navController,
            title = "Шорты",
            onClick = {},
            shadow = false,
            searchText = searchText,
            onValueChange = {},
            icon = "share",
            backBtn = true,
            modifier = Modifier,
            lazyListState = LazyListState()
        )
    }, bottomBar = {
        Box(
            modifier = Modifier
                .background(White)
                .height(100.dp)
                .shadow(
                    1.dp,
                    shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp),
                    clip = true
                ),
            contentAlignment = Alignment.Center
        ) {
            AppThemeButton(
                text = "Добавить в корзину",
                onClick = {
                    cartViewModel.addCartProduct(productId = productId, productQuantity = 1)
                })
        }
    }) {


        if (homeViewModel.productByIdUiState.isLoading || homeViewModel.productByIdUiState.error.isNotEmpty()) {
            ProductShimmer()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (image in productByIdData?.images ?: emptyList()) {
                        AsyncImage(
                            modifier = Modifier
                                .width(280.dp)
                                .height(420.dp),
                            model = image,
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(
                            onClick = {
                                if (selected == 0) {
                                    selected = 1
                                } else {
                                    selected = 0
                                }
                                favoritesViewModel.favoritesToggle(
                                    productId = productId,
                                    productType = "product"
                                )
                            }, modifier = Modifier
                                .size(40.dp)
                                .shadow(elevation = 2.dp, clip = true, shape = CircleShape)
                                .clip(CircleShape)
                                .background(color = White)
                        ) {
                            Image(
                                painter = painterResource(
                                    id = if (selected == 0) {
                                        R.drawable.heart_icon
                                    } else {
                                        R.drawable.filled_heart_icon
                                    }
                                ),
                                contentDescription = "heart",
                                modifier = Modifier
                                    .size(15.dp)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${productByIdData?.name}",
                            fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                            fontSize = 24.sp
                        )
                        Text(
                            text = "${productByIdData?.price} TJS",
                            fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                            fontSize = 24.sp
                        )
                    }
                    Text(
                        text = "${productByIdData?.category?.name}",
                        fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                        fontSize = 12.sp,
                        color = Gray
                    )
                    Row(
                        modifier = Modifier.padding(top = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        productByIdData?.avgRating?.let {
                            RatingBar(
                                startRating = it,
                                selectable = false,
                                ratingSelected = {},
                                text = productByIdData.avgRating.toString(),
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate(
                                            Screen.ProductDetailsScreen.passProductDetails(
                                                productId = productId
                                            )
                                        )
                                    }
                                    .size(12.dp)
                            )
                        }
                    }

                    Text(
                        modifier = Modifier.padding(vertical = 25.dp),
                        text = "${productByIdData?.desc}",
                        fontFamily = FontFamily(Font(R.font.metropolis_regular))
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .clickable(onClick = {navController.navigate(Screen.RatingScreen.passProductDetails(productId))})
                    )
                    {
                        Divider(
                            modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter),
                            thickness = 0.5.dp,
                            color = LightGray
                        )
                        Text(
                            text = "Отзывы",
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(start = 20.dp)
                                .padding(vertical = 10.dp)
                                .align(Alignment.CenterStart)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.profile_item_button),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 20.dp)
                                .size(10.dp)
                                .align(
                                    Alignment.CenterEnd
                                )
                        )
                        Divider(
                            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
                            thickness = 0.5.dp,
                            color = LightGray
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Вам может понравиться",
                            fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                            fontSize = 20.sp,

                            )
                        Text(
                            text = "12 товаров",
                            fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                            fontSize = 13.sp,
                            color = Gray
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 150.dp)
                            .horizontalScroll(
                                rememberScrollState()
                            ),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        for (product in similarProducts) {
                            SaleProduct(
                                sale_label = "new",
                                width = 150.dp,
                                onClick = {
                                    navController.navigate(
                                        Screen.ProductDetailsScreen.passProductDetails(
                                            product.id!!
                                        )
                                    )
                                },
                                product = product
                            )
                        }
                    }
                }
            }
        }
    }
}