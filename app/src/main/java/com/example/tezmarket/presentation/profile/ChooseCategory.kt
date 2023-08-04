package com.example.tezmarket.presentation.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.presentation.categories.CategoriesViewModel
import com.example.tezmarket.presentation.categories.CategoryCard
import com.example.tezmarket.ui.common.AppThemeButton
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.common.AppThemeTopText
import com.example.tezmarket.ui.theme.Background

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChooseCategory(navController: NavController, categoriesViewModel: CategoriesViewModel = hiltViewModel()) {
    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val categories = categoriesViewModel.categoriesUiState.data?.data ?: emptyList()

    Scaffold(topBar = {
        AppThemeTopBar(
            title = "",
            backBtn = true,
            shadow = false,
            icon = "",
            searchText = searchText,
            onValueChange = { searchText.value = it },
            onClick = { /*TODO*/ },
            lazyListState = LazyListState(),
            navController = navController
        )
    }, backgroundColor = Background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
        ) {
            AppThemeTopText(
                text = "Выберите категорию",
                color = Transparent,
                shadow = false,
                modifier = Modifier
            )
//            Spacer(modifier = Modifier.height(50.dp))
            for (items in categories) {
                CategoryCard(category = items, modifier = Modifier.clickable(onClick = {navController.navigate(Screen.ChooseAttributs.passCategoryId(categoryId = items.id ?: 1))}))
            }


        }
    }
}