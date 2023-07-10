package com.example.tezmarket.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tezmarket.R
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.presentation.home.HomeViewModel
import com.example.tezmarket.ui.common.*
import com.example.tezmarket.ui.theme.Background

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegistrationScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }

    Scaffold(
        backgroundColor = Background,
        topBar = {
            AppThemeTopBar(
                title = "",
                navController = navController,
                shadow = false,
                onClick = {},
                searchText = searchText,
                onValueChange = {},
                backBtn = false,
                icon = "",
                modifier = Modifier,
                lazyListState = LazyListState()
            )
        }) {

        val localFocusManager = LocalFocusManager.current

        var name by remember {
            mutableStateOf(TextFieldValue(""))
        }

        var email by remember {
            mutableStateOf(TextFieldValue(""))
        }

        var password by remember {
            mutableStateOf(TextFieldValue(""))
        }

        val maxLength = 30

        Column(modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                localFocusManager.clearFocus()
            })
        }) {

            AppThemeTopText(text = "Регистрация", color = Transparent, shadow = false, modifier = Modifier)
            Spacer(modifier = Modifier.height(73.dp))
            NameTextfied(
                textFieldValue = name,
                onTextFieldValueChange = {
                    if (it.text.length <= maxLength) {
                        name = it
                    }
                }
            )
            MailTextfield(
                textFieldValue = email,
                onTextFieldValueChange = {
                    if (it.text.length <= maxLength) {
                        email = it
                    }
                })
            PasswordField(
                textFieldValue = password,
                onTextFieldValueChange = {
                    if (it.text.length <= maxLength) {
                        password = it
                    }
                })
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                TextButton(
                    onClick = { navController.navigate(Screen.AuthorizationScreen.route) },
                    modifier = Modifier
                        .padding(end = 10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "У вас уже есть аккаунт?",
                            fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            color = Color.Black,
                            letterSpacing = 0.6.sp
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_icon),
                            contentDescription = "arrow",
                            modifier = Modifier
                                .padding(start = 3.dp)
                                .size(24.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(28.dp))
            AppThemeButton(text = "Зарегистрироваться") {
                navController.navigate(Screen.HomeScreen.route)
            }
            Spacer(modifier = Modifier.height(158.dp))
            AccountTextWithIcon(text = "Или зарегистрируйтесь с помощью учетной записи MyTcell")
        }
    }
}


@Preview(name = "RegistrationScreenPreview")
@Composable
fun DefaultPreview() {
    RegistrationScreen(navController = rememberNavController())
}