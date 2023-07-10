package com.example.tezmarket.presentation.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.ui.common.AppThemeButton
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.theme.Background

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddressAddScreen(
    navController: NavController,
    adressId: Int,
    adressViewModel: AdressViewModel = hiltViewModel(),
) {
    var name by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var address by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var city by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var region by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var index by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var country by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var search = remember {
        mutableStateOf(TextFieldValue(""))
    }


    Scaffold(topBar = {
        AppThemeTopBar(
            title = if (adressId == 0) "Добавление адреса доставки" else "Изменение адреса доставки",
            backBtn = true,
            shadow = true,
            icon = "",
            onClick = { /*TODO*/ },
            navController = navController,
            modifier = Modifier,
            lazyListState = LazyListState(),
            searchText = search,
            onValueChange = {}
        )
    }, backgroundColor = Background) {

        Column(
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            AddNameTextfied(
                textFieldValue = name,
                onTextFieldValueChange = { name = it },
                label = "Полное имя"
            )
            AddNameTextfied(
                textFieldValue = address,
                onTextFieldValueChange = { address = it },
                label = "Адрес"
            )
            AddNameTextfied(
                textFieldValue = city,
                onTextFieldValueChange = { city = it },
                label = "Город"
            )
            AddNameTextfied(
                textFieldValue = region,
                onTextFieldValueChange = { region = it },
                label = "Область / Район / Регион"
            )
            AddNameTextfied(
                textFieldValue = index,
                onTextFieldValueChange = { index = it },
                label = "Индекс / Почтовый индекс)"
            )
            AddNameTextfied(
                textFieldValue = country,
                onTextFieldValueChange = { country = it },
                label = "Страна"
            )
            Spacer(modifier = Modifier.height(20.dp))
            AppThemeButton(text =  if (adressId == 0) "СОХРАНИТЬ АДРЕС" else "ИЗМЕНИТЬ АДРЕСС") {
                if (adressId == 0){
                    adressViewModel.addAdress(
                        adressName = name.text,
                        cityId = 1,
                        regionName = region.text,
                        zipCode = index.text.toInt()
                    )
                }
                else{
                    adressViewModel.updateAdress(
                        adressId = adressId,
                        adressName = name.text,
                        cityId = 1,
                        regionName = region.text,
                        zipCode = index.text.toInt()
                    )
                }
//                navController.navigate(Screen.AdressesScreen.route)
            }
        }
    }
}

@Preview
@Composable
fun ShowAddress() {
    AddressAddScreen(navController = rememberNavController(), adressId = 1)
}