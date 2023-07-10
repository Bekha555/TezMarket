package com.example.tezmarket.presentation.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tezmarket.presentation.brand.BrandFilterList
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.common.BottomAppBarButton
import com.example.tezmarket.ui.theme.Background


@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint(
    "RememberReturnType", "UnrememberedMutableState",
    "UnusedMaterialScaffoldPaddingParameter"
)
@Composable
fun BrandFilterScreen(navController: NavController) {
    var search by remember {
        mutableStateOf(TextFieldValue(""))
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val localFocusManager = LocalFocusManager.current
    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }

    Scaffold(topBar = {
        AppThemeTopBar(
            title = "",
            navController = navController,
            shadow = true,
            onClick = {},
            searchText = searchText,
            onValueChange = {},
            backBtn = true,
            icon = "v",
            modifier = Modifier,
            lazyListState = LazyListState()
        )
    },
        bottomBar = {
            BottomAppBarButton(
                confirmText = "Применить",
                confirm = {},
                cancelText = "Отказаться",
                cancel = {},
                bottomElevation = 50.dp
            )
        }) {
        Column(modifier = Modifier
            .background(color = Background)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }) {


            TextField(
                value = search,
                onValueChange = { search = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search"
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(onSearch = { keyboardController?.hide() }),
                placeholder = { Text(text = "Поиск") },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color.White
                ),
                maxLines = 1,
                shape = RoundedCornerShape(23.dp),
                //   textStyle = TextStyle.Default.copy(fontSize = 28.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            )
            BrandFilterList()
        }
    }
}

@Preview
@Composable
fun BrandFilterScreen() {
    BrandFilterScreen(navController = rememberNavController())
}