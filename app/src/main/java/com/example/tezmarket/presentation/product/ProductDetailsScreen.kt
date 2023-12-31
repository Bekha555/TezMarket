package com.example.tezmarket.presentation.product

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
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
import com.example.tezmarket.ui.theme.LightPurple
import com.example.tezmarket.ui.theme.Primary
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.roundToInt

@OptIn(ExperimentalPagerApi::class)
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
        homeViewModel.getAdvertisementById(advertisement_id = productId)
    })

    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val productByIdData = homeViewModel.productByIdUiState.data?.data
    val similarProducts = homeViewModel.simularProductUiState.data?.data ?: emptyList()
    val images = productByIdData?.images ?: emptyList()

    Log.d("debug", productByIdData.toString())

    var selected by remember {
        mutableStateOf(if (productByIdData?.isFavorite == true) 1 else 0)
    }
    var inCart by remember {
        mutableStateOf(if (productByIdData?.addedToCart == true) 1 else 0)
    }
    var visible by remember {
        mutableStateOf(true)
    }
    var selectedImageIndex by remember { mutableStateOf(0) }

    Scaffold(topBar = {
        if (visible) {
            AppThemeTopBar(
                navController = navController,
                title = productByIdData?.name ?: "",
                onClick = {},
                shadow = false,
                searchText = searchText,
                onValueChange = {},
                icon = "",
                backBtn = true,
                modifier = Modifier,
                lazyListState = LazyListState()
            )
        }
    }, bottomBar = {
        if (visible) {
            if (homeViewModel.productByIdUiState.isLoading || homeViewModel.productByIdUiState.error.isEmpty()) {
            Box(
                modifier = Modifier
                    .background(White)
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                AppThemeButton(
                    text = if (productByIdData?.addedToCart == false && inCart == 0) {
                        "Добавить в корзину"
                    } else {
                        "Товар уже в корзине"
                    },
                    onClick = {
                        if (productByIdData?.addedToCart == false && inCart == 0) {
                            cartViewModel.addCartProduct(productId = productId, productQuantity = 1)
                            inCart = 1
                        }
                    })
            }
        }
        }
    }) {

        val pagerState = rememberPagerState()

        if (homeViewModel.productByIdUiState.isLoading || homeViewModel.productByIdUiState.error.isNotEmpty()) {
            ProductShimmer()
        } else {

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(
                    animationSpec = TweenSpec(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ),
                exit = fadeOut(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Background)
                        .padding(it)
                        .verticalScroll(rememberScrollState())
                ) {
                    HorizontalPager(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(420.dp),
                        count = images.size,
                        state = pagerState
                    ) { page ->
                        val imageUrl = images[page]
                        AsyncImage(
                            model = imageUrl,
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            onLoading = {},
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable(onClick = {
                                    selectedImageIndex = page
                                    visible = false
                                })
                        )
                    }
                    if (images.size > 1) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            for (i in 0 until images.size) {
                                val color =
                                    if (pagerState.currentPage == i) Primary else LightPurple
                                Box(
                                    modifier = Modifier
                                        .padding(horizontal = 4.dp)
                                        .size(8.dp)
                                        .background(color = color, shape = CircleShape)
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.padding(vertical = 10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp)
                                .padding(horizontal = 10.dp),
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
                                        id = if (selected == 0 && productByIdData?.isFavorite == false) {
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
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),
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
                            color = Gray,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                        Row(
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .padding(horizontal = 10.dp),
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
                            modifier = Modifier.padding(vertical = 25.dp, horizontal = 10.dp),
                            text = "${productByIdData?.desc}",
                            fontFamily = FontFamily(Font(R.font.metropolis_regular))
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                                .clickable(onClick = {
                                    navController.navigate(
                                        Screen.RatingScreen.passProductDetails(
                                            productId
                                        )
                                    )
                                })
                        )
                        {
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.TopCenter),
                                thickness = 0.5.dp,
                                color = LightGray
                            )
                            Text(
                                text = "Отзывы",
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .padding(vertical = 10.dp)
                                    .align(Alignment.CenterStart)
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.profile_item_button),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(end = 10.dp)
                                    .size(10.dp)
                                    .align(
                                        Alignment.CenterEnd
                                    )
                            )
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter),
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
                                fontSize = 20.sp
                            )
                            Text(
                                text = "6 товаров",
                                fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                                fontSize = 13.sp,
                                color = Gray
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                                .horizontalScroll(
                                    rememberScrollState()
                                ),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            for (product in similarProducts) {
                                Box(modifier = Modifier.padding(start = 10.dp)) {
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





            AnimatedVisibility(

                visible = !visible,
                enter = fadeIn(
                    animationSpec = TweenSpec(
                        durationMillis = 700,
                        easing = FastOutSlowInEasing
                    )
                ),
                exit = fadeOut(
                    animationSpec = tween(
                        durationMillis = 700,
                        easing = FastOutSlowInEasing
                    )
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Black)
                ) {
                    var zoom by remember { mutableStateOf(1f) }
                    var offsetX by remember { mutableStateOf(0f) }
                    var offsetY by remember { mutableStateOf(0f) }

                    val imageSize = remember { mutableStateOf(IntSize.Zero) }
                    val containerSize = remember { mutableStateOf(IntSize.Zero) }


                    AsyncImage(
                        model = images[selectedImageIndex],
                        contentDescription = null,
                        modifier = Modifier
                            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                            .graphicsLayer(
                                scaleX = zoom,
                                scaleY = zoom,
                            )
                            .pointerInput(Unit) {
                                detectTransformGestures { _, pan, gestureZoom, _ ->
                                    zoom *= gestureZoom

                                    if (zoom < 1f) {
                                        zoom = 1f
                                    } else if (zoom > 5f) { // You can adjust the maximum zoom level as needed
                                        zoom = 5f
                                    }

                                    // Increase the panning speed when zoomed in
                                    val zoomedPanSpeed = 1.3f
                                    offsetX += pan.x * zoom * zoomedPanSpeed
                                    offsetY += pan.y * zoom * zoomedPanSpeed

                                    // Limit panning to prevent the image from going beyond the screen boundaries
                                    val maxX =
                                        (imageSize.value.width * zoom - containerSize.value.width) / 2
                                    val maxY =
                                        (imageSize.value.height * zoom - containerSize.value.height) / 2
                                    offsetX = offsetX.coerceIn(-maxX, maxX)
                                    offsetY = offsetY.coerceIn(-maxY, maxY)
                                }
                            }
                            .onSizeChanged { size -> containerSize.value = size }
                            .fillMaxSize()
                            .onGloballyPositioned {
                                imageSize.value = IntSize(it.size.width, it.size.height)
                            }
                    )
                    IconButton(
                        onClick = { visible = !visible },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "close",
                            tint = White
                        )
                    }
                }

            }
        }
    }
}
