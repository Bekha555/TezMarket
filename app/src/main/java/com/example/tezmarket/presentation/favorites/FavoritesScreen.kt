package com.example.tezmarket.presentation.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieCancellationBehavior
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.tezmarket.R
import com.example.tezmarket.navigation.BottomNavigation
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.common.AppThemeTopText
import com.example.tezmarket.ui.common.Filters
import com.example.tezmarket.ui.theme.Gray


@Composable
fun FavoriteScreen(
    navController: NavController,
    lazyListState: LazyListState,
    favoritesViewModel: FavoritesViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = Unit, block = {
        favoritesViewModel.getFavoriteProducts()
    })

    val favoriteProducts = favoritesViewModel.favoriteProducts.collectAsState()

    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.loading_animation)
    )
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        speed = 2f,
        restartOnPlay = false,
        cancellationBehavior = LottieCancellationBehavior.Immediately
    )
    var visible by remember {
        mutableStateOf(true)
    }
    if (lazyListState.firstVisibleItemIndex > 0) {
        visible = false
    }
    if (lazyListState.firstVisibleItemIndex == 0) {
        visible = true
    }
    val scrollState = rememberScrollState()


    Scaffold(
        topBar = {
            AppThemeTopBar(
                title = if (scrollState.value > 110) {
                    "Избранное"
                } else {
                    ""
                },
                icon = "search",
                onClick = { /*TODO*/ },
                shadow = false,
                navController = navController,
                backBtn = false,
                searchText = searchText,
                onValueChange = {},
                modifier = Modifier,
                lazyListState = LazyListState()
            )
        },
        bottomBar = { BottomNavigation(navController) }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            AppThemeTopText(
                text = "Избранное",
                color = White,
                shadow = false,
                modifier = Modifier
            )
            Filters(onClick = {}, grid = {}, filter = {}, filterOption = "")
            if (favoriteProducts.value.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 250.dp, horizontal = 50.dp)
                ) {
                    Text(
                        text = "Похоже вы нечего не добавили в  избранное",
                        color = Gray,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                            .padding(horizontal = 20.dp)
                    )
                }
            }
            for (product in favoriteProducts.value) {
                FavoriteSaleProduct(navController = navController, favoriteProduct = product)
            }
        }

    }

}


val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0