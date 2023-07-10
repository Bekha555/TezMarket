@file:Suppress("UNUSED_EXPRESSION")

package com.example.tezmarket.presentation.categories

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCancellationBehavior
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.tezmarket.R
import com.example.tezmarket.navigation.BottomNavigation
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.common.AppThemeTopText

@SuppressLint("FrequentlyChangedStateReadInComposition", "RememberReturnType")
@Composable
fun CategoriesScreenTest(
    navcontroller: NavController,
    lazyListState: LazyListState,
    categoriesViewModel: CategoriesViewModel = hiltViewModel()
) {
    var visible by remember {
        mutableStateOf(true)
    }
    if (lazyListState.firstVisibleItemIndex > 0) {
        visible = false
    }
    if (lazyListState.firstVisibleItemIndex == 0) {
        visible = true
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
    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }

    val categories = categoriesViewModel.categoriesUiState.data?.data ?: emptyList()

    Scaffold(topBar = {
        Column {
            AppThemeTopBar(
                title = if (!visible) {
                    "Категории"
                } else {
                    ""
                },
                backBtn = false,
                shadow = false,
                icon = "search",
                searchText = searchText,
                onValueChange = {},
                onClick = { /*TODO*/ },
                navController = navcontroller,
                modifier = Modifier,
                lazyListState = lazyListState
            )
        }
    },
        bottomBar = {
            BottomNavigation(navController = navcontroller)
        }
    ) {
        Box(modifier = Modifier.padding(it)) {

            LazyColumn(modifier = Modifier.fillMaxWidth(), state = lazyListState) {
                item {
                    AppThemeTopText(
                        text = "Категории",
                        color = White,
                        shadow = true,
                        modifier = Modifier
                    )
                }
                if (categoriesViewModel.categoriesUiState.isLoading) {
                    item {
                        Box(modifier = Modifier.fillMaxSize()) {
                            LottieAnimation(
                                composition = composition,
                                progress = progress,
                                modifier = Modifier
                                    .size(150.dp)
                                    .align(Alignment.Center),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
                if (categories.isNotEmpty()){
                    items(categories) { item ->
                        CategoryCard(category = item)
                    }
            }

            }

        }
    }
}

val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0

@Preview
@Composable
fun ShowCategories() {
    CategoriesScreenTest(navcontroller = rememberNavController(), lazyListState = LazyListState())
}
