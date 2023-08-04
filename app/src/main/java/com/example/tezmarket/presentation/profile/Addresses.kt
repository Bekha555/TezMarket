package com.example.tezmarket.presentation.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.presentation.cart.AddressCard
import com.example.tezmarket.presentation.profile.AdressViewModel
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.theme.Background

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Addresses(navController: NavController, adressViewModel: AdressViewModel = hiltViewModel()) {

    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val adresses = adressViewModel.adressItems.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            AppThemeTopBar(
                title = "Адреса доставки",
                backBtn = true,
                icon = "",
                searchText = searchText,
                onValueChange = {},
                onClick = {  },
                navController = navController,
                shadow = true,
                modifier = Modifier,
                lazyListState = LazyListState()
            )
        },
        backgroundColor = Background,
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddressAddScreen.passAdressId(0)) },
                backgroundColor = Black,
                contentColor = White,
                modifier = Modifier.padding(bottom = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "", tint = White,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        }

    ) {

        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(adresses){
                if (it != null) {
                    AddressCard(navController, adress = it)
                }
            }
        }
    }
}

@Preview
@Composable
fun ShowAddresses() {
    Addresses(navController = rememberNavController())
}