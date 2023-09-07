import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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
import com.example.tezmarket.presentation.favorites.FavoritesViewModel
import com.example.tezmarket.presentation.home.HomeViewModel
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.common.SaleProduct
import com.example.tezmarket.ui.theme.Background
import com.example.tezmarket.ui.theme.Gray
import com.example.tezmarket.ui.theme.LightGray
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.roundToInt


@OptIn(ExperimentalPagerApi::class)
@Composable
fun AdvertisementDetailsScreen(
    navController: NavController,
    advertisementId: Int,
    homeViewModel: HomeViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit, block = {
        Log.d("debug", advertisementId.toString())
        homeViewModel.getAdvertisementById(advertisement_id = advertisementId)
        homeViewModel.getOtherAdvertisement(advertisement_id = advertisementId)
    })

    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }

    val productByIdData = homeViewModel.advertisementByIduiState.data?.data
    // val similarProducts = homeViewModel.simularProductUiState.data?.data ?: emptyList()
    val otherProducts = homeViewModel.otherAdvertisementUiState.data?.data ?: emptyList()
    val images = productByIdData?.images ?: emptyList()

    Log.d("debug", productByIdData.toString())

    var selected by remember {
        mutableStateOf(if (productByIdData?.isFavorite == true) 1 else 0)
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
    }) {

        val pagerState = rememberPagerState()

        if (homeViewModel.advertisementByIduiState.isLoading || homeViewModel.advertisementByIduiState.error.isNotEmpty()) {
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
                    Box(modifier = Modifier.fillMaxWidth()) {
                        HorizontalPager(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(420.dp)
                                .align(Alignment.Center),
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
                                    .padding(bottom = 5.dp)
                                    .align(Alignment.BottomCenter),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                for (i in 0 until images.size) {
                                    val color =
                                        if (pagerState.currentPage == i) Gray else LightGray
                                    Box(
                                        modifier = Modifier
                                            .padding(horizontal = 4.dp)
                                            .size(8.dp)
                                            .background(color = color, shape = CircleShape)
                                    )
                                }
                            }
                        }

                    }

                    Text(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        text = "${productByIdData?.createdAt}",
                        fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                        fontSize = 12.sp,
                        color = Gray
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
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

                    Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp).padding(bottom = 5.dp)) {
                        Text(
                            text = "${productByIdData?.category?.title}",
                            fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                            fontSize = 12.sp,
                            color = Gray,
                            modifier = Modifier.align(Alignment.TopStart)
                        )
                        IconButton(
                            onClick = {
                                if (selected == 0) {
                                    selected = 1
                                } else {
                                    selected = 0
                                }
                                favoritesViewModel.favoritesToggle(
                                    productId = advertisementId,
                                    productType = "product"
                                )
                            }, modifier = Modifier.padding(top = 5.dp)
                                .size(40.dp)
                                .shadow(elevation = 2.dp, clip = true, shape = CircleShape)
                                .clip(CircleShape)
                                .background(color = Color.White)
                                .align(Alignment.TopEnd)
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



                    Text(
                        modifier = Modifier.padding(vertical = 0.dp, horizontal = 15.dp),
                        text = "${productByIdData?.desc}",
                        fontFamily = FontFamily(Font(R.font.metropolis_regular))
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .horizontalScroll(
                                rememberScrollState()
                            ),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                    }


                    if (homeViewModel.advertisementByIduiState.data?.data?.attributes?.isNotEmpty() == true) {
                        Text(
                            text = "Характеристики",
                            fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                            fontSize = 24.sp,
                            modifier = Modifier.padding(start = 15.dp)
                        )
                        for (item in homeViewModel.advertisementByIduiState.data?.data?.attributes!!) {
                            Column(
                                modifier = Modifier
                                    .padding(top = 10.dp)
                                    .padding(horizontal = 15.dp)
                            ) {
                                Text(text = item.name.toString(), fontSize = 14.sp, color = Gray)
                                Text(text = item.value.toString())
                            }
                        }
                        Text(
                            text = "Информация о продавце",
                            fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                            fontSize = 24.sp,
                            modifier = Modifier.padding(start = 15.dp, top = 10.dp)
                        )
                        CallSection(
                            name = productByIdData?.client?.name.toString(),
                            number = productByIdData?.client?.phone!!
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Вам может понравиться",
                            fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                            fontSize = 20.sp
                        )
                        Text(
                            text = "${otherProducts.size} товар(ов)",
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
                        for (product in otherProducts) {
                            Box(modifier = Modifier.padding(start = 15.dp)) {
                                SaleProduct(
                                    sale_label = "new",
                                    width = 150.dp,
                                    onClick = {
                                        navController.navigate(
                                            Screen.AdvertisementDetailsScreen.passAdvertisementDetails(
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
                        .background(Color.Black)
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
                            tint = Color.White
                        )
                    }
                }

            }
        }
    }
}


@Composable
fun CallSection(name: String, number: String) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .padding(top = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = name,
                fontFamily = FontFamily(Font(R.font.metropolis_bold))
            )
            Text(
                text = number,
                fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                color = Gray
            )
        }
        IconButton(
            onClick = {
                val u = Uri.parse("tel:" + "+" + number)
                val i = Intent(Intent.ACTION_DIAL, u)
                try {
                    context.startActivity(i)
                } catch (s: SecurityException) {
                    Toast.makeText(context, "Чтото пошло не так", Toast.LENGTH_LONG)
                        .show()
                }
            }, modifier = Modifier
                .size(30.dp)
                .align(
                    Alignment.CenterEnd
                )
        ) {
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.call_icon),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        }

    }
}
