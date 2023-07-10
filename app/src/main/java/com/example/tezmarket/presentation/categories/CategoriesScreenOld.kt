package com.example.tezmarket.presentation.categories

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tezmarket.navigation.BottomNavigation
import com.example.tezmarket.presentation.profile.OrderCard
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.theme.Background
import com.example.tezmarket.ui.theme.Primary
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun CategoriesScreenOld(navController: NavController) {
    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(topBar = {
        AppThemeTopBar(
            navController = navController,
            title = "Категории",
            onClick = {},
            shadow = true,
            searchText = searchText,
            onValueChange = {},
            backBtn = true,
            icon = "",
            modifier = Modifier,
            lazyListState = LazyListState()
        )},
        bottomBar = {
            BottomNavigation(navController = navController)
        },
        backgroundColor = Background
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
        ) {
            TabCategories(
                selectedTabIndex = pagerState.currentPage,
                pagerState = pagerState,
                onSelectedTab = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(it)
                    }
                }
            )
            HorizontalPager(state = pagerState, count = 3) { index ->
                Column(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())) {
                    when (index) {
                        0 -> repeat(10) { OrderCard() }
                        1 -> repeat(3) { OrderCard() }
                        2 -> repeat(6) { OrderCard() }
                    }
                }
            }
        }
    }
    }
}




@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabCategories(selectedTabIndex: Int, pagerState: PagerState, onSelectedTab: (Int) -> Unit) {
    val listOfCategoryTypes = listOf("Женские", "Мужские", "Детские")
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.White,
        indicator = {tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState = pagerState, tabPositions),
                color = Primary
            )
        }
    ) {
        var isSelected by remember {
            mutableStateOf(false)
        }
        listOfCategoryTypes.forEach {
            Tab(
                selected = isSelected,
                onClick = {
                    isSelected = !isSelected
                    onSelectedTab(listOfCategoryTypes.indexOf(it))
                },
                text = { Text(text = it, color = Color.Black, fontSize = 16.sp) }
            )
        }
    }
}



@Preview
@Composable
fun CategoriesScreenPreview(){
    CategoriesScreenOld(navController = rememberNavController())
}