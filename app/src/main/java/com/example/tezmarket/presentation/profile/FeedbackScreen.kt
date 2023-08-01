package com.example.tezmarket.presentation.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.common.AppThemeTopText

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun FeedbackScreen() {
    var search = remember {
        mutableStateOf(TextFieldValue(""))
    }

    Scaffold(topBar = {
        AppThemeTopBar(
            title = "",
            backBtn = true,
            shadow = false,
            icon = "",
            searchText = search,
            onValueChange = {},
            onClick = { /*TODO*/ },
            lazyListState = rememberLazyListState(),
            navController = rememberNavController()
        )
    }, bottomBar = { FloatingBottomBar(onClick = { /*TODO*/ }, text = "Оставить Отзыв") }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                AppThemeTopText(
                    text = "Обратная связь",
                    color = Transparent,
                    shadow = false,
                    modifier = Modifier.padding(top = 18.dp)
                )
            }
//         item { ReviewCard(reviewInfo = )}
        }
    }
}