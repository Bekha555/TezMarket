@file:Suppress("UNUSED_EXPRESSION")

package com.example.tezmarket.presentation.profile

import android.annotation.SuppressLint
import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tezmarket.R
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.common.AppThemeTopText
import com.example.tezmarket.ui.theme.Background
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "RememberReturnType")
@Composable
fun MyOrderScreen(navController: NavController) {
    val tabItems = listOf("Доставлено", "Обработка", "Отменено")
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }

    Scaffold(topBar = {
        Column {
            AppThemeTopBar(
                title = "",
                backBtn = true,
                shadow = false,
                icon = "search",
                searchText = searchText,
                onValueChange = {},
                onClick = { /*TODO*/ },
                modifier = Modifier,
                lazyListState = LazyListState(),
                navController = rememberNavController()
            )
            AppThemeTopText(
                text = "Мои заказы",
                color = Transparent,
                shadow = false,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
    },
//        bottomBar = {
//        BottomNavigation(navController = navController)
//    },
        backgroundColor = Background) {
        Column(modifier = Modifier.padding(it)) {

            TabRow(
                selectedTabIndex = pagerState.currentPage,
                backgroundColor = Transparent,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier
                            .pagerTabIndicatorOffset(pagerState, tabPositions)
                            .width(0.dp)
                            .height(0.dp)
                    )
                }, divider = { null },
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                tabItems.forEachIndexed { index, title ->
                    val color = remember { Animatable(Black) }
                    LaunchedEffect(key1 = pagerState.currentPage == index) {
                        color.animateTo(if (pagerState.currentPage == index) Black else Transparent)
                    }
                    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {

                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            modifier = Modifier
                                .background(
                                    color = color.value,
                                    shape = RoundedCornerShape(30.dp)
                                )
                                .height(40.dp)
                        ) {
                            Text(
                                text = title,
                                style = if (pagerState.currentPage == index)
                                    TextStyle(
                                        color = White, fontSize = 16.sp, fontFamily = FontFamily(
                                            Font(R.font.metropolis_bold)
                                        )
                                    )
                                else
                                    TextStyle(
                                        color = Black, fontSize = 16.sp, fontFamily = FontFamily(
                                            Font(R.font.metropolis_regular)
                                        )
                                    )
                            )
                        }
                    }
                }
            }



            HorizontalPager(
                count = tabItems.size,
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) { page ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp)
//                        .verticalScroll(state = rememberScrollState())
                ) {
                    when (page) {
                        0 -> items(5) { OrderCard() }
                        1 -> items(2) { OrderCard() }
                        2 -> items(10) { OrderCard() }
                    }
                }
            }

        }
    }
}

private object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.0f, 0.0f, 0.0f, 0.0f)
}

@Preview
@Composable
fun ShowOrderScreen() {
    MyOrderScreen(navController = rememberNavController())
}