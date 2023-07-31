package com.example.tezmarket.presentation.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.presentation.categories.CategoriesViewModel
import com.example.tezmarket.presentation.categories.CategoryCard
import com.example.tezmarket.ui.common.AppThemeTopBar

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnrememberedMutableState", "UnusedMaterialScaffoldPaddingParameter")

@Composable
fun CategoriesScreen(
    navController: NavController,
    categoriesViewModel: CategoriesViewModel = hiltViewModel()
) {
    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val categories = categoriesViewModel.categoriesUiState.data?.data ?: emptyList()

    Scaffold(topBar = {
        AppThemeTopBar(
            navController = rememberNavController(),
            icon = "search",
            onClick = {},
            shadow = true,
            title = "Категории",
            searchText = searchText,
            onValueChange = {},
            backBtn = true,
            modifier = Modifier,
            lazyListState = LazyListState()
        )
    }) {
        Column {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(categories) { category ->
                    CategoryCard(modifier = Modifier.clickable {
                        navController.navigate(Screen.ShowAllScreen.passProductName(category.id ?: 1))
                    }, category = category)
                }
            }

        }
    }
}

