//package com.example.tezmarket.presentation.auth
//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.LazyListState
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.example.tezmarket.R
//import com.example.tezmarket.navigation.BottomNavigation
//import com.example.tezmarket.ui.common.AppThemeButton
//import com.example.tezmarket.ui.common.AppThemeTopBar
//
//@OptIn(ExperimentalFoundationApi::class)
//@SuppressLint("UnrememberedMutableState", "UnusedMaterialScaffoldPaddingParameter")
//
//@Composable
//fun CategoriesScreenDel(navController: NavController) {
//    val list = mutableStateListOf(
//        "Топы",
//        "Shirts & Blouses",
//        "Рубашки и блузки",
//        "Вязаные вещи",
//        "Блейзеры",
//        "Верхняя одежда",
//        "Брюки",
//        "Джинсы",
//        "Шорты",
//        "Юбки",
//        "Платья",
//        "Топы",
//        "Shirts & Blouses",
//        "Рубашки и блузки",
//        "Вязаные вещи",
//        "Блейзеры",
//        "Верхняя одежда",
//        "Брюки",
//        "Джинсы",
//        "Шорты",
//        "Юбки",
//        "Платья"
//    )
//
//    Scaffold(topBar = {
//        AppThemeTopBar(
//            navController = rememberNavController(),
//            icon = "search",
//            onClick = {},
//            shadow = true,
//            title = "Категории",
//            backBtn = true,
//            modifier = Modifier,
//            lazyListState = LazyListState()
//        )
//    }, bottomBar = {
//        BottomNavigation(navController = navController)
//    }) {
//        Column {
//            Spacer(modifier = Modifier.height(15.dp))
//
//            AppThemeButton(text = "ПОСМОТРЕТЬ ВСЕ ТОВАРЫ") {}
//
//            Spacer(modifier = Modifier.height(15.dp))
//
//            LazyColumn(modifier = Modifier.fillMaxWidth()) {
//                items(list) { text ->
//                    Box(Modifier.fillMaxWidth()) {
//                        Text(
//                            text = text, fontSize = 16.sp, fontFamily = FontFamily(
//                                Font(R.font.metropolis_regular)
//                            ), modifier = Modifier
//                                .padding(start = 40.dp)
//                                .padding(vertical = 17.dp)
//                        )
//                        Divider(
//                            Modifier
//                                .fillMaxWidth()
//                                .align(Alignment.BottomCenter)
//                        )
//                    }
//                }
//
//
//            }
//
//
//        }
//    }
//}
//
//@Preview
//@Composable
//fun ShowCategoriesScreen() {
//    CategoriesScreenDel(rememberNavController())
//}