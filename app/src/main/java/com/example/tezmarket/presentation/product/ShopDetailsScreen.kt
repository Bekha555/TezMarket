package com.example.tezmarket.presentation.product

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
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tezmarket.R
import com.example.tezmarket.presentation.ProductShimmer
import com.example.tezmarket.presentation.home.HomeViewModel
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.theme.Background
import com.example.tezmarket.ui.theme.Gray
import com.example.tezmarket.ui.theme.Primary
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ShopDetailScreen(
    navController: NavController,
    shopId: Int,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit, block = {
        Log.d("debug", shopId.toString())
        homeViewModel.getShopById(shop_id = shopId)
    })

    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }

    val shopByIdData = homeViewModel.shopByIdUiState.data?.data
    // val similarProducts = homeViewModel.simularProductUiState.data?.data ?: emptyList()
    Log.d("debug", shopByIdData.toString())


    var visible by remember {
        mutableStateOf(true)
    }

    Scaffold(topBar = {
        if (visible) {
            AppThemeTopBar(
                navController = navController,
                title = shopByIdData?.name ?: "",
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
    },
        bottomBar = {
            CallButton(number = shopByIdData?.phone.toString())
        }
    ) {

        if (homeViewModel.shopByIdUiState.isLoading || homeViewModel.shopByIdUiState.error.isNotEmpty()) {
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
                    AsyncImage(
                        model = shopByIdData?.logo,
                        contentScale = ContentScale.FillBounds,
                        contentDescription = null,
                        onLoading = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(420.dp)
                   )


                    Column(
                        modifier = Modifier.padding(vertical = 10.dp)
                    ) {
                        Text(
                            text = "${shopByIdData?.name}",
                            fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                            fontSize = 24.sp,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                        Text(
                            text = "Адрес: ${shopByIdData?.city?.title}, ${shopByIdData?.address}",
                            fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                            fontSize = 12.sp,
                            color = Gray,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )

                        Text(
                            modifier = Modifier.padding(vertical = 25.dp, horizontal = 10.dp),
                            text = "${shopByIdData?.description}",
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
                    }

                }
            }
        }
    }
}


@Composable
fun CallButton(number: String) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .background(White)
            .height(100.dp),
        contentAlignment = Alignment.Center
    )
    {
        Button(
            onClick = { val u = Uri.parse("tel:" + "+" + number)
                val i = Intent(Intent.ACTION_DIAL, u)
                try {
                    context.startActivity(i)
                } catch (s: SecurityException) {
                    Toast.makeText(context, "Чтото пошло не так", Toast.LENGTH_LONG)
                        .show()
                } },
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Primary),
            elevation = ButtonDefaults.elevation(defaultElevation = 5.dp),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(70.dp)
        ) {
            Box(modifier = Modifier.height(40.dp).width(160.dp).padding(start = 10.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.call_icon),
                    contentDescription = "call",
                    modifier = Modifier
                        .size(27.dp)
                        .align(Alignment.CenterStart)
                )
                Text(
                    text = "Позвонить", fontSize = 16.sp, color = White, fontWeight = FontWeight.SemiBold, modifier = Modifier.align(
                        Alignment.TopCenter
                    )
                )
                Text(
                    text = number, fontSize = 12.sp, color = White, modifier = Modifier.align(
                        Alignment.BottomCenter
                    )
                )
            }

        }
    }
}