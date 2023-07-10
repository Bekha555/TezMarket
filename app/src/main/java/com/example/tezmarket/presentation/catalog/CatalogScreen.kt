package com.example.tezmarket.presentation.catalog

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tezmarket.R
import com.example.tezmarket.data.remote.model.products.Data
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.ui.common.*
import com.example.tezmarket.ui.theme.Background
import com.example.tezmarket.ui.theme.Gray
import com.example.tezmarket.ui.theme.Primary
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CatalogScreen(navController: NavController) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    var visible by remember {
        mutableStateOf(true)
    }
    var search = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val coroutineScope = rememberCoroutineScope()
    val state = rememberLazyGridState()
    val localFocusManager = LocalFocusManager.current

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetBackgroundColor = Background,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Box(modifier = Modifier
                .height(350.dp)
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        localFocusManager.clearFocus()
                    })
                }) {
                Divider(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .width(70.dp)
                        .height(8.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .align(Alignment.TopCenter), color = Gray
                )
                Text(
                    text = "Сортировать по",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 35.dp)
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(BottomCenter)
                        .padding(bottom = 10.dp)
                ) {
                    item { Sorting() }
                }
            }
        }) {
        Scaffold(topBar = {
            AppThemeTopBar(
                navController = rememberNavController(),
                backBtn = true,
                icon = "search",
                shadow = false,
                onClick = {},
                title = "",
                modifier = Modifier,
                lazyListState = LazyListState(),
                searchText = search,
                onValueChange = {}
            )
        }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Background)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = White)
                        .height(20.dp)
                )
                AppThemeTopText(
                    text = "Женские топы",
                    color = White,
                    shadow = false,
                    modifier = Modifier
                )
                Filters(onClick = { /*TODO*/ }, grid = {
                    visible = !visible
                }, filter = { coroutineScope.launch { modalBottomSheetState.show() } })

                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn(animationSpec = tween(300)),
                    exit = fadeOut(animationSpec = tween(300))
                ) {
                    LazyColumn() {
                        items(8) {
                            SaleProductList(navController, it)
                        }
                    }
                }
                AnimatedVisibility(
                    visible = !visible,
                    enter = fadeIn(animationSpec = tween(300)),
                    exit = fadeOut(animationSpec = tween(300))
                ) {
                    LazyVerticalGrid(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Transparent),
                        columns = GridCells.Fixed(2),
                        content = {
                            items(9) {
                                SaleProduct(
                                    sale_label = "",
                                    width = 165.dp,
                                    onClick = { navController.navigate(Screen.ProductDetailsScreen.route) },
                                    product = Data()
                                )
                            }
                        },
                        state = state,
                        contentPadding = PaddingValues(start = 20.dp, top = 10.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Sorting() {
    var selectedOption by remember { mutableStateOf("") }
    val options = listOf(
        "Популярные",
        "Новейшие",
        "Отзывам клиентов",
        "Цена: от низкой к высокой",
        "Цена: от высокой к низкой"
    )
    Column(Modifier.fillMaxWidth()) {
        options.forEach { option ->
            Row(
                verticalAlignment = CenterVertically,
                modifier = Modifier
                    .requiredHeight(48.dp)
                    .fillMaxWidth()
                    .selectable(selected = (selectedOption == option),
                        onClick = { selectedOption = option })
                    .background(
                        color = if (selectedOption == option) {
                            Primary
                        } else {
                            Transparent
                        }
                    )
            ) {
                Text(
                    text = option,
                    color = if (selectedOption == option) {
                        White
                    } else {
                        Black
                    },
                    fontWeight = if (selectedOption == option) {
                        FontWeight.Bold
                    } else {
                        FontWeight.Normal
                    },
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(start = 20.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ShowCatalogScreen() {
    CatalogScreen(navController = rememberNavController())
}

